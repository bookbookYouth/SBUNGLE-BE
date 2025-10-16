package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OnboardSecondRequest(
        @Schema(
                description = """
                        선호하는 장르
                        - NOVEL: 소설
                        - POETRY_ESSAY: 시/에세이
                        - SELF_DEVELOPMENT: 자기계발
                        - HUMANITIES_SOCIAL: 인문/사회
                        - ART: 예술
                        - ECONOMY_MANAGEMENT: 경제/경영
                        """
        )
        @NotNull
        List<PreferredGenre> preferredGenres
) {
}
