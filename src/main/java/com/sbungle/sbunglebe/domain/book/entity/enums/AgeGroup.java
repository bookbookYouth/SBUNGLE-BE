package com.sbungle.sbunglebe.domain.book.entity.enums;

public enum AgeGroup {
    TEENS, TWENTIES, THIRTIES, FORTY_PLUS;

    public static AgeGroup fromAge(Integer age) {
        if (age == null) return TWENTIES; // 기본값
        if (age < 20) return TEENS;
        if (age < 30) return TWENTIES;
        if (age < 40) return THIRTIES;
        return FORTY_PLUS;
    }
}