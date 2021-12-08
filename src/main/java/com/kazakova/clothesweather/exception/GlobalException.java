package com.kazakova.clothesweather.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GlobalException {

    private final String message;
    private final HttpStatus httpStatus;

    public GlobalException(String message,
                           HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
