package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdatePreferredGenreRequest(
        @NotNull
        List<PreferredGenre> preferredGenres
) {
}
