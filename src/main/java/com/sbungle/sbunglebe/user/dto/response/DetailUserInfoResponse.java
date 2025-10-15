package com.sbungle.sbunglebe.user.dto.response;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import com.sbungle.sbunglebe.user.domain.enums.SocialLoginType;
import lombok.Builder;

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
        PreferredGenre preferredGenre,
        PreferredType preferredType

) {
    public static DetailUserInfoResponse from(UserEntity user) {
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
                .preferredGenre(user.getPreferredGenre())
                .preferredType(user.getPreferredType())
                .build();
    }
}
