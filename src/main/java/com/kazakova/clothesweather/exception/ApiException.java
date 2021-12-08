package com.kazakova.clothesweather.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// TODO переименовать
@Getter
public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;

    public ApiException(String message,
                        HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
