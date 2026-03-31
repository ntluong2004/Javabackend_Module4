package com.tlu.learning.ex_full.chapter2.chapter2_ex2;

import com.tlu.learning.ex_full.chapter2.chapter2_ex2.ApiResponse;
import com.tlu.learning.ex_full.chapter2.chapter2_ex2.AppException;
import com.tlu.learning.ex_full.chapter2.chapter2_ex2.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }
}