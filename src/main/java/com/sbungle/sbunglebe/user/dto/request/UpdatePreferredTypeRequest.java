package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public record UpdatePreferredTypeRequest(
        @NotNull
        @UniqueElements
        List<PreferredType> preferredTypes
) {
}
