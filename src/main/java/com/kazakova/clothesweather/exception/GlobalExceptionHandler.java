package com.kazakova.clothesweather.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException e) {
        log.info(">> GlobalExceptionHandler handleRequestException  RequestException={}", e.getClass().getName());
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        GlobalException globalException = new GlobalException(
                e.getMessage(),
                badRequest
        );
        log.info("<< GlobalExceptionHandler handleRequestException globalException={}", globalException);
        return new ResponseEntity<>(globalException, badRequest);
    }

    // TODO add Exception
}
