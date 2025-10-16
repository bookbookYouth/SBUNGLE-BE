package com.sbungle.sbunglebe.user.dto.response;

public record UserDetailResponse(
        Long id,
        String email,
        String name,
        String imageUrl,
        String nickName,
        String socialLoginType
) {
}
