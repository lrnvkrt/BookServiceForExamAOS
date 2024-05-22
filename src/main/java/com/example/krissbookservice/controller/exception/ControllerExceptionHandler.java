package com.example.krissbookservice.controller.exception;

import com.example.krissbookservice.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @Data
    @AllArgsConstructor
    public static class NotFoundResponse {
        private String message;
        private String key;
        private String value;
        private String entity;
    }
    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<NotFoundResponse> handleException(NotFoundException e) {
        return new ResponseEntity<>(
                new NotFoundResponse(e.getMessage(), e.getKey(), e.getKeyValue(), e.getEntity()), HttpStatus.NOT_FOUND
        );
    }
}
