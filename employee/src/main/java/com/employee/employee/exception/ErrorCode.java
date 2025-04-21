package com.employee.employee.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    EMPLOYEE_NOT_EXISTED(40401, "Employee is not exit!", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXISTED(40402, "Department is not exit!", HttpStatus.NOT_FOUND);
    int code;
    String message;
    HttpStatus status;
}
