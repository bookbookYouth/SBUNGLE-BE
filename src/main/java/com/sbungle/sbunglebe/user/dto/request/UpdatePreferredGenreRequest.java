package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public record UpdatePreferredGenreRequest(
        @NotNull
        @UniqueElements
        List<PreferredGenre> preferredGenres
) {
}
