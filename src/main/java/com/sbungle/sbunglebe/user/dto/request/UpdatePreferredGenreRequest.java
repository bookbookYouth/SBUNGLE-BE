package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import jakarta.validation.constraints.NotNull;

public record UpdatePreferredGenreRequest(
        @NotNull
        PreferredGenre preferredGenre
) {
}
