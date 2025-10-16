package com.sbungle.sbunglebe.user.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateAgeRequest(
        @NotNull
        int age
) {
}
