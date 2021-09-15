package com.kazakova.clothesweather.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Season {
    SUMMER("summer"),
    AUTUMN("autumn"),
    WINTER("winter"),
    SPRING("spring");


    private String code;

    private Season(String code) {
        this.code=code;
    }

    @JsonCreator
    public static Season decode(final String code) {
        return Stream.of(Season.values())
                .filter(targetEnum -> targetEnum.code.equals(code))
                .findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
