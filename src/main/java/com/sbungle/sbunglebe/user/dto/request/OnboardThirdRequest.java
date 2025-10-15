package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import jakarta.validation.constraints.NotNull;

public record OnboardThirdRequest(
        @NotNull
        PreferredType preferredType
) {
}
