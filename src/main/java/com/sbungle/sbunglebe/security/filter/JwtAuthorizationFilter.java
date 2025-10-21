package com.sbungle.sbunglebe.security.filter;

import com.sbungle.sbunglebe.global.redis.entity.RefreshToken;
import com.sbungle.sbunglebe.global.redis.service.RefreshTokenRedisService;
import com.sbungle.sbunglebe.security.exception.SecurityErrorCode;
import com.sbungle.sbunglebe.security.exception.TokenException;
import com.sbungle.sbunglebe.security.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

import static com.sbungle.sbunglebe.user.constants.SecurityConstants.ACCESS_TOKEN_COOKIE;


@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final RefreshTokenRedisService refreshTokenRedisService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = extractAccessTokenFromCookie(request);

        if (accessToken == null || accessToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        log.debug("Access token from cookie: {}", accessToken);

        validateAndReissue(response, accessToken);
        setAuthentication(accessToken);
        filterChain.doFilter(request, response);

    }

    private String extractAccessTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        return java.util.Arrays.stream(cookies)
                .filter(cookie -> ACCESS_TOKEN_COOKIE.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }


    private void setAuthentication(String accessToken) {
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private void validateAndReissue(HttpServletResponse response, String accessToken) {
        if (!jwtProvider.validateToken(accessToken)) {

            String userId = jwtProvider.getSubject(accessToken);
            Optional<RefreshToken> optionalRefresh = refreshTokenRedisService.findRefreshToken(userId);
            // refresh 만료 - redis 존재하지 않음
            checkRefreshExpire(optionalRefresh);

            // refresh redis에 존재하고 유효
            reissueAccess(response, optionalRefresh);
        }
    }


    private void checkRefreshExpire(Optional<RefreshToken> optionalRefresh) {
        if (optionalRefresh.isEmpty()) {
            log.debug("Refresh token is not found. Redirect to login page.");
            throw new TokenException(SecurityErrorCode.REFRESH_EXPIRED);
        }
    }

    private void reissueAccess(HttpServletResponse response, Optional<RefreshToken> optionalRefresh) {
        String refreshToken = optionalRefresh.get().getRefreshToken();
        if (jwtProvider.validateToken(refreshToken)) {
            // 재발급
            String newAccessToken = jwtProvider.reissueWithRefresh(refreshToken);

            ResponseCookie newAccessCookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE, newAccessToken)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .sameSite("Lax")
                    .maxAge(Duration.ofHours(1))
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, newAccessCookie.toString());

        }
    }


}
