package com.sbungle.sbunglebe.user.controller;

import com.sbungle.sbunglebe.user.dto.response.SocialLoginResponse;
import com.sbungle.sbunglebe.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1")
public class AuthController {

    private final AuthService authService;


    @GetMapping("/login/kakao")
//    @PostMapping("/login/kakao")
    public ResponseEntity<SocialLoginResponse> kakaoLogin(
            @RequestParam("code") String accessCode
    ) {

        SocialLoginResponse socialLoginResponse = authService.loginOrRegisterKakao(accessCode);
        String accessWithBearer = authService.createAccessTokenWhenLogin(socialLoginResponse.userId());

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, accessWithBearer)
                .body(socialLoginResponse);

    }

}
