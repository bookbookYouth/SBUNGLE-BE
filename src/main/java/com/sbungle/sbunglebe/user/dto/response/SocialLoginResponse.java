package com.sbungle.sbunglebe.user.dto.response;


public record SocialLoginResponse(
        String userId,
        String email,
        String name,
        boolean enabled
) {
}
