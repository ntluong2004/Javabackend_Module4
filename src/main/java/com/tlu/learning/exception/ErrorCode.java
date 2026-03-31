package com.tlu.learning.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    STUDENT_NOTFOUND(40401, "Student not found!", HttpStatus.NOT_FOUND)
    ;
    int code;
    String message;
    HttpStatus status;
}
