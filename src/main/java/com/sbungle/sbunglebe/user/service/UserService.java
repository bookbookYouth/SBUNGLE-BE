package com.sbungle.sbunglebe.user.service;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.dto.response.DetailUserInfoResponse;
import com.sbungle.sbunglebe.user.exception.AuthenticationErrorCode;
import com.sbungle.sbunglebe.user.exception.AuthenticationException;
import com.sbungle.sbunglebe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /**
     * 유저 관련 공통 사용 로직 - 유저 조회. 없으면 예외 발생
     * @param userId 유저 String ID
     * @return UserEntity 조회된 유저 엔티티
     */
    public UserEntity findUserByIdOrThrow(String userId) {
        return userRepository.findByUserId(userId)
                             .orElseThrow(() -> new AuthenticationException(AuthenticationErrorCode.USER_NOT_FOUND));
    }

    public DetailUserInfoResponse getUserInfo(String userId) {
        UserEntity user = findUserByIdOrThrow(userId);
        return DetailUserInfoResponse.from(user);
    }

}
