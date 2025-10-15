package com.sbungle.sbunglebe.user.dto.response;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.domain.enums.Gender;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;

public record OnboardUserResponse(
        String userId,
        String email,
        String name,
        String nickName,
        boolean enabled,
        Gender gender,
        Integer age,
        PreferredGenre preferredGenre,
        PreferredType preferredType
) {
    public static OnboardUserResponse from(UserEntity user) {
        return new OnboardUserResponse(
                user.getUserId(),
                user.getEmail(),
                user.getName(),
                user.getNickName(),
                user.isEnabled(),
                user.getGender(),
                user.getAge(),
                user.getPreferredGenre(),
                user.getPreferredType()
        );
    }
}
