package com.sbungle.sbunglebe.user.dto.response;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.UserPreferredGenre;
import com.sbungle.sbunglebe.user.domain.UserPreferredType;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import com.sbungle.sbunglebe.user.domain.enums.SocialLoginType;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DetailUserInfoResponse(

        String userId,
        String email,
        String name,
        String imageUrl,
        String nickName,
        boolean enabled,
        SocialLoginType socialLoginType,
        Gender gender,
        Integer age,
        List<PreferredGenre> preferredGenres,
        List<PreferredType> preferredTypes

) {
    public static DetailUserInfoResponse from(UserEntity user) {
        List<PreferredGenre> genres = user.getPreferredGenres().stream()
                .map(UserPreferredGenre::getGenre)
                .collect(Collectors.toList());
        List<PreferredType> types = user.getPreferredTypes().stream()
                .map(UserPreferredType::getPreferredType)
                .collect(Collectors.toList());

        return DetailUserInfoResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .imageUrl(user.getImageUrl())
                .nickName(user.getNickName())
                .enabled(user.isEnabled())
                .socialLoginType(user.getSocialLoginType())
                .gender(user.getGender())
                .age(user.getAge())
                .preferredGenres(genres)
                .preferredTypes(types)
                .build();
    }
}
