package com.sbungle.sbunglebe.user.controller;

import com.sbungle.sbunglebe.user.dto.response.SocialLoginResponse;
import com.sbungle.sbunglebe.user.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

import static com.sbungle.sbunglebe.user.constants.SecurityConstants.ACCESS_TOKEN_COOKIE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1")
public class AuthController {

    private final AuthService authService;


    //    @GetMapping("/login/kakao")
    @PostMapping("/login/kakao")
    public ResponseEntity<SocialLoginResponse> kakaoLogin(
            @RequestParam("code") String accessCode
    ) {

        SocialLoginResponse socialLoginResponse = authService.loginOrRegisterKakao(accessCode);
        String accessToken = authService.createAccessTokenWhenLogin(socialLoginResponse.userId());

        ResponseCookie cookie = createAccessCookie(accessToken);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(socialLoginResponse);
    }

    private ResponseCookie createAccessCookie(String accessToken) {
        return ResponseCookie.from(ACCESS_TOKEN_COOKIE, accessToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("Lax")
                .maxAge(Duration.ofHours(1))
                .build();
    }

    private final TestLoginService testLoginService;

    /**
     * 무조건 PK id=1 유저로 액세스 토큰 발급 (개발/로컬 전용)
     */
    @PostMapping("/test-login")
    public ResponseEntity<TestLoginService.TestLoginResult> testLogin() {
        return ResponseEntity.ok(testLoginService.issueAccessTokenForUserId1());
    }

}
