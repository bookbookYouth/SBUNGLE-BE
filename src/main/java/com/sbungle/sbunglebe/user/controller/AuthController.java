package com.sbungle.sbunglebe.user.controller;

import com.sbungle.sbunglebe.user.dto.response.SocialLoginResponse;
import com.sbungle.sbunglebe.user.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sbungle.sbunglebe.user.constants.SecurityConstants.ACCESS_TOKEN_COOKIE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login/kakao")
    public ResponseEntity<SocialLoginResponse> kakaoLogin(
            @RequestParam("code") String accessCode,
            HttpServletResponse response
    ) {

        SocialLoginResponse socialLoginResponse = authService.loginOrRegisterKakao(accessCode);
        String accessToken = authService.createAccessTokenWhenLogin(socialLoginResponse.userId());

        Cookie cookie = createAccessCookie(accessToken);
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK)
                .body(socialLoginResponse);

    }

    private Cookie createAccessCookie(String accessToken) {
        Cookie cookie = new Cookie(ACCESS_TOKEN_COOKIE, accessToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        return cookie;
    }

}
