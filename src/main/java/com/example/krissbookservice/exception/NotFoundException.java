package com.example.krissbookservice.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String entity;
    private final String key;
    private final String keyValue;

    public NotFoundException(String entity, String key, String keyValue) {
        super("Could not find entity [%s] with key [%s] of value [%s]".formatted(entity, key, keyValue));
        this.entity = entity;
        this.key = key;
        this.keyValue = keyValue;
    }
}
