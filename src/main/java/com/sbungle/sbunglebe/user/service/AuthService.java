package com.sbungle.sbunglebe.user.service;

import com.sbungle.sbunglebe.global.redis.service.RefreshTokenRedisService;
import com.sbungle.sbunglebe.security.jwt.JwtProvider;
import com.sbungle.sbunglebe.user.client.KakaoResourceClient;
import com.sbungle.sbunglebe.user.client.KakaoTokenClient;
import com.sbungle.sbunglebe.user.converter.AuthConverter;
import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.dto.response.KakaoResourceServerResponse;
import com.sbungle.sbunglebe.user.dto.response.KakaoTokenResponse;
import com.sbungle.sbunglebe.user.dto.response.SocialLoginResponse;
import com.sbungle.sbunglebe.user.exception.AuthenticationException;
import com.sbungle.sbunglebe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import static com.sbungle.sbunglebe.user.constants.SecurityConstants.TOKEN_PREFIX;
import static com.sbungle.sbunglebe.user.exception.AuthenticationErrorCode.KAKAO_AUTHENTICATION_FAILED;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoResourceClient kakaoResourceClient;
    private final AuthConverter authConverter;


    private final RefreshTokenRedisService refreshTokenRedisService;
    @Value("${jwt.expiration.refresh}")
    private Long REFRESH_TOKEN_EXPIRE_TIME;

    @Value("${etc.kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${etc.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;


    @Transactional
    public SocialLoginResponse loginOrRegisterKakao(String accessCode) {
        KakaoResourceServerResponse kakaoUserInfo = requestToKakao(accessCode);
        log.debug("카카오 사용자 정보: {}", kakaoUserInfo);

        UserEntity userEntity = getOrSave(kakaoUserInfo);

        return authConverter.toSocialLoginResponse(userEntity);
    }

    public String createAccessTokenWhenLogin(String userId) {

        Authentication authentication = jwtProvider.getAuthenticationFromUserId(userId);
        String accessToken = jwtProvider.generateAccessToken(authentication, userId);
        String refreshToken = jwtProvider.generateRefreshToken(authentication, userId);

        refreshTokenRedisService.saveRefreshToken(userId, refreshToken, REFRESH_TOKEN_EXPIRE_TIME);

        return accessToken;
    }


    private UserEntity getOrSave(KakaoResourceServerResponse serverResponse) {
        UserEntity userEntity = userRepository.findByEmail(serverResponse.kakaoAccount().email())
                .orElseGet(() -> UserEntity.fromKakaoResponse(serverResponse));
        return userRepository.save(userEntity);
    }

    private KakaoResourceServerResponse requestToKakao(String accessCode) {

        try {

            MultiValueMap<String, Object> formData = authConverter.toKakaoTokenRequest(accessCode, KAKAO_CLIENT_ID, KAKAO_REDIRECT_URI);
            KakaoTokenResponse kakaoToken = kakaoTokenClient.getKakaoToken(formData);
            log.debug("카카오 토큰 정보: {}", kakaoToken);

            String bearerHeader = TOKEN_PREFIX + kakaoToken.accessToken();
            return kakaoResourceClient.getUserInfo(bearerHeader);

        } catch (Exception e) {
            log.error("카카오 사용자 정보 요청 실패: {}", e.getMessage());
            throw new AuthenticationException(KAKAO_AUTHENTICATION_FAILED, e.getMessage());
        }
    }

}
