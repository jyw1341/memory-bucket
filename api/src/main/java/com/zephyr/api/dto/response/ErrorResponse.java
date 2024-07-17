package com.zephyr.api.dto.response;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.validation = new HashMap<>();
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
