package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdatePreferredTypeRequest(
        @NotNull
        List<PreferredType> preferredTypes
) {
}
