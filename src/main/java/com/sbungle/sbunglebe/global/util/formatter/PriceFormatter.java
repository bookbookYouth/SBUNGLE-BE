package com.sbungle.sbunglebe.global.util.formatter;

import com.fasterxml.jackson.annotation.JsonValue;

import java.text.NumberFormat;
import java.util.Locale;

public record PriceFormatter(int price) {
    private static final NumberFormat FORMATTER = NumberFormat.getInstance(Locale.KOREA);


    @JsonValue
    public String asString() {
        return FORMATTER.format(price) + "원";
    }

}
