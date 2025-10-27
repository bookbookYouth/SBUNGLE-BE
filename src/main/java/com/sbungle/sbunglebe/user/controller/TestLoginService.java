package com.sbungle.sbunglebe.user.controller;

import com.sbungle.sbunglebe.security.jwt.JwtProvider;
import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.dto.response.SimpleUserInfoResponse;
import com.sbungle.sbunglebe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Profile({"local","dev"})
public class TestLoginService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public TestLoginResult issueAccessTokenForUserId1() {
        // ★ PK = 1 고정
        UserEntity user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("테스트용 유저(id=1)가 없습니다. 더미데이터를 먼저 넣어주세요."));

        // JwtProvider 규약에 맞춰 Authentication 생성 (subject로 userId 사용)
        Authentication auth = jwtProvider.getAuthenticationFromUserId(user.getUserId());

        // Access 토큰 발급 (필요 시 refresh도 가능)
        String accessToken = jwtProvider.generateAccessToken(auth, user.getUserId());

        return new TestLoginResult(
                "Bearer",
                accessToken,
                SimpleUserInfoResponse.from(user)
        );
    }

    public record TestLoginResult(
            String tokenType,
            String accessToken,
            SimpleUserInfoResponse user
    ) {}
}
