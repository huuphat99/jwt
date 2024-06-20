package com.javainuse.bootmysqlcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NumberFormatException.class, NullPointerException.class})
    public ResponseEntity<String> handleNumberFormatException(NumberException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid page value: " + ex.getMessage());
    }
}
