package com.tlu.learning.ex_full.chapter2.chapter2_ex3;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // Standard Success/Uncategorized
    SUCCESS(1000, "Success", HttpStatus.OK),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    // Employee Related Errors
    USER_NOT_FOUND(404, "Employee not found", HttpStatus.NOT_FOUND),
    USER_EXISTED(400, "Employee already exists", HttpStatus.BAD_REQUEST),

    // Department Related Errors
    DEPARTMENT_NOT_EXISTED(404, "Department does not exist", HttpStatus.NOT_FOUND),
    INVALID_DEPARTMENT_ID(400, "Invalid Department ID format", HttpStatus.BAD_REQUEST),

    // Validation Errors
    INVALID_KEY(400, "Invalid message key", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, "You do not have permission", HttpStatus.UNAUTHORIZED)
    ;

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}