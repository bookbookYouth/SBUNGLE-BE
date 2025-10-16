package com.sbungle.sbunglebe.user.dto.response;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.UserPreferredGenre;
import com.sbungle.sbunglebe.user.domain.UserPreferredType;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record SimpleUserInfoResponse(

        String userId,
        String email,
        Gender gender,
        Integer age,
        List<PreferredGenre> preferredGenres,
        List<PreferredType> preferredTypes

) {
    public static SimpleUserInfoResponse from(UserEntity user) {
        List<PreferredGenre> genres = user.getPreferredGenres().stream()
                .map(UserPreferredGenre::getGenre)
                .collect(Collectors.toList());
        List<PreferredType> types = user.getPreferredTypes().stream()
                .map(UserPreferredType::getPreferredType)
                .collect(Collectors.toList());

        return SimpleUserInfoResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .gender(user.getGender())
                .age(user.getAge())
                .preferredGenres(genres)
                .preferredTypes(types)
                .build();
    }
}
