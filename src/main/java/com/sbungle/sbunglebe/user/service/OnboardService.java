package com.sbungle.sbunglebe.user.service;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import com.sbungle.sbunglebe.user.dto.response.SimpleUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OnboardService {

    private final UserService userService;


    @Transactional
    public SimpleUserInfoResponse updateGenderAge(String userId, Gender gender, int age) {
        UserEntity user = userService.findUserByIdOrThrow(userId);
        user.updateGender(gender);
        user.updateAge(age);
        return SimpleUserInfoResponse.from(user);
    }

    @Transactional
    public SimpleUserInfoResponse updateGender(String userId, Gender gender) {
        UserEntity user = userService.findUserByIdOrThrow(userId);
        user.updateGender(gender);
        return SimpleUserInfoResponse.from(user);
    }

    @Transactional
    public SimpleUserInfoResponse updateAge(String userId, int age) {
        UserEntity user = userService.findUserByIdOrThrow(userId);
        user.updateAge(age);
        return SimpleUserInfoResponse.from(user);
    }

    @Transactional
    public SimpleUserInfoResponse updatePreferredGenre(String userId, PreferredGenre preferredGenre) {
        UserEntity user = userService.findUserByIdOrThrow(userId);
        user.updatePreferredGenre(preferredGenre);
        return SimpleUserInfoResponse.from(user);
    }


    @Transactional
    public SimpleUserInfoResponse updatePreferredType(String userId, PreferredType preferredType) {
        UserEntity user = userService.findUserByIdOrThrow(userId);
        user.updatePreferredType(preferredType);
        return SimpleUserInfoResponse.from(user);
    }


}

