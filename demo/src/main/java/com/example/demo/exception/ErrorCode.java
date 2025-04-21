package com.example.demo.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    STUDENT_NOT_EXIST(40401, "Student is not exit!", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus status;
}
