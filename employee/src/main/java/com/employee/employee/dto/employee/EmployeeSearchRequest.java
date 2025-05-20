package com.employee.employee.dto.employee;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EmployeeSearchRequest {
    String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dobFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dobTo;

    Gendar gendar;
    String salaryRange;
    String phone;
    Integer departmentId;
}
