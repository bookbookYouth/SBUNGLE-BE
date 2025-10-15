package com.sbungle.sbunglebe.user.service;

import com.sbungle.sbunglebe.user.domain.UserEntity;
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

    public UserEntity findUserByIdOrThrow(String userId) {
        return userRepository.findByUserId(userId)
                             .orElseThrow(() -> new AuthenticationException(AuthenticationErrorCode.USER_NOT_FOUND));
    }

}
