package com.sbungle.sbunglebe.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PreferredGenre {

    NOVEL("소설"),
    POETRY_ESSAY("시/에세이"),
    SELF_DEVELOPMENT("자기계발"),
    HUMANITIES_SOCIAL("인문/사회"),
    ART("예술"),
    ECONOMY_MANAGEMENT("경제/경영"),;

    private final String description;

}
