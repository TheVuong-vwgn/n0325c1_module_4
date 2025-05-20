package com.example.exercise.dto.employee;

import com.example.exercise.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)public class EmployeeResponse {
    Integer id;
    String name;
    LocalDate dob;
    @Enumerated(EnumType.STRING)
    Gender gender;
    Double salary;
    String phone;

    String departmentName;
}
