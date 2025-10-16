package com.sbungle.sbunglebe.user.dto.request;

import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OnboardThirdRequest(
        @Schema(
                description = """
                        선호하는 유형
                        - CALM_WARM: 잔잔하고 따뜻한
                        - THRILLING: 긴장감 넘치는
                        - PHILOSOPHICAL_REFLECTIVE: 철학적이고 사유적인
                        - SENSUAL_DREAMY: 감성적이고 몽환적인
                        - CHEERFUL_LIGHT: 유쾌하고 가벼운
                        - DEPRESSED_HEAVY: 우울하고 먹먹한
                        - REALISTIC_SOCIAL: 현실적이고 사회적인
                        - WITTY_CREATIVE: 기발하고 창의적인
                        """
        )
        @NotNull
        List<PreferredType> preferredTypes
) {
}
