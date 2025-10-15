package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.Gender;
import jakarta.validation.constraints.NotNull;

public record UpdateGenderRequest(
        @NotNull
        Gender gender
) {
}
