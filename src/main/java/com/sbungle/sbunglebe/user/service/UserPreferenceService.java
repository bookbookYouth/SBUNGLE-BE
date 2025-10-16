package com.sbungle.sbunglebe.user.service;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.UserPreferredGenre;
import com.sbungle.sbunglebe.user.domain.UserPreferredType;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import com.sbungle.sbunglebe.user.dto.response.SimpleUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserPreferenceService {

    private final UserService userService;


    @Transactional
    public SimpleUserInfoResponse updatePreferredGenre(String userId, List<PreferredGenre> preferredGenres) {
        UserEntity user = userService.findUserByIdOrThrow(userId);

        user.getPreferredGenres().clear();
        preferredGenres.stream()
                    .map(preferredGenre -> UserPreferredGenre.of(user, preferredGenre))
                    .forEach(user::addPreferredGenre);

        return SimpleUserInfoResponse.from(user);
    }


    @Transactional
    public SimpleUserInfoResponse updatePreferredType(String userId, List<PreferredType> preferredTypes) {
        UserEntity user = userService.findUserByIdOrThrow(userId);

        user.getPreferredTypes().clear();
        preferredTypes.stream()
                .map(preferredType -> UserPreferredType.of(user, preferredType))
                .forEach(user::addPreferredType);

        return SimpleUserInfoResponse.from(user);
    }


}

