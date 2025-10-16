package com.sbungle.sbunglebe.user.service;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.UserPreferredGenre;
import com.sbungle.sbunglebe.user.domain.UserPreferredType;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
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
        preferredGenres.forEach(genre -> {
            UserPreferredGenre userPreferredGenre = UserPreferredGenre.of(user, genre);
            user.addPreferredGenre(userPreferredGenre);
        });

        return SimpleUserInfoResponse.from(user);
    }


    @Transactional
    public SimpleUserInfoResponse updatePreferredType(String userId, List<PreferredType> preferredTypes) {
        UserEntity user = userService.findUserByIdOrThrow(userId);

        user.getPreferredTypes().clear();
        preferredTypes.forEach(type -> {
            UserPreferredType userPreferredType = UserPreferredType.of(user, type);
            user.addPreferredType(userPreferredType);
        });

        return SimpleUserInfoResponse.from(user);
    }


}

