package com.sbungle.sbunglebe.user.service;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import com.sbungle.sbunglebe.user.dto.request.OnboardFirstRequest;
import com.sbungle.sbunglebe.user.dto.request.OnboardSecondRequest;
import com.sbungle.sbunglebe.user.dto.request.OnboardThirdRequest;
import com.sbungle.sbunglebe.user.dto.response.OnboardUserResponse;
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
    public OnboardUserResponse updateGenderAge(String userId, Gender gender, int age) {
        UserEntity user = userService.findUserByIdOrThrow(userId);
        user.updateGenderAndAge(gender, age);
        return OnboardUserResponse.from(user);
    }

    @Transactional
    public OnboardUserResponse updatePreferredGenre(String userId, PreferredGenre preferredGenre) {
        UserEntity user = userService.findUserByIdOrThrow(userId);
        user.updatePreferredGenre(preferredGenre);
        return OnboardUserResponse.from(user);
    }


    @Transactional
    public OnboardUserResponse updatePreferredType(String userId, PreferredType preferredType) {
        UserEntity user = userService.findUserByIdOrThrow(userId);
        user.updatePreferredType(preferredType);
        return OnboardUserResponse.from(user);
    }


}

