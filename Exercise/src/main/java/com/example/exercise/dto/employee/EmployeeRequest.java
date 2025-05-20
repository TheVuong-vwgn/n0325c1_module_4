package com.example.exercise.dto.employee;

import com.example.exercise.contraint.DobConstraint;
import com.example.exercise.dto.department.DepartmentRequest;
import com.example.exercise.enums.Gender;
import com.example.exercise.model.Department;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    @NotBlank(message = "Tên không được phép để trống")
    @Pattern(regexp = "[a-zA-ZÀ-Ỹ ]+", message = "Tên không hợp lệ")
    @Length(min = 3, message = "Tên phải lớn hơn 3 ký tự")
    String name;
    @NotNull(message = "Ngày sinh là bắt buộc")
    @DobConstraint(min = 18, message = "Bạn tuổi")
    LocalDate dob;
    @NotNull(message = "Giới tính là bắt buộc")
    @Enumerated(EnumType.STRING)
    Gender gender;
    @NotNull(message = "Lương không được phép để trống")
    Double salary;
    @NotNull(message = "SDT không được phép để trống")
    @Pattern(regexp = "^(0[2-9]{1}[0-9]{8,9})$\n", message = "SDT không hợp lệ")
    String phone;
    @Valid
    @NotNull(message = "Chức vụ không được để trống")
    Integer department_Id;

    DepartmentRequest department;
}
