package com.sbungle.sbunglebe.domain.book.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    NOVEL("소설"),
    POEM("시/에세이"),
    DEVELOPMENT("자기계발"),
    SOCIAL("인문/사회"),
    ART("예술"),
    ECONOMICS("경제/경영");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
