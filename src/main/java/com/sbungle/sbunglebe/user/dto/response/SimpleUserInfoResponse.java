package com.sbungle.sbunglebe.user.dto.response;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import lombok.Builder;

@Builder
public record SimpleUserInfoResponse(

        String userId,
        String email,
        Gender gender,
        Integer age,
        PreferredGenre preferredGenre,
        PreferredType preferredType

) {
    public static SimpleUserInfoResponse from(UserEntity user) {
        return SimpleUserInfoResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .gender(user.getGender())
                .age(user.getAge())
                .preferredGenre(user.getPreferredGenre())
                .preferredType(user.getPreferredType())
                .build();
    }
}
