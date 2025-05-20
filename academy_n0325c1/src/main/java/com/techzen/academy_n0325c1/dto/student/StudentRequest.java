package com.techzen.academy_n0325c1.dto.student;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.techzen.academy_n0325c1.contraint.DobConstraint;
import com.techzen.academy_n0325c1.dto.clazz.ClazzRequest;
import com.techzen.academy_n0325c1.model.Clazz;
import com.techzen.academy_n0325c1.model.StudentProfile;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
public class StudentRequest {
//    @NotNull(message = "Tên không được phép null")
//    @NotEmpty(message = "Tên không được phép empty")
    @NotBlank(message = "Tên không được phép để trống")
    @Pattern(regexp = "[a-zA-ZÀ-Ỹ ]+", message = "Tên không hợp lệ")
    @Length(min = 3, message = "Tên phải lớn hơn 3 ký tự")
    String name;
    @NotNull(message = "Điểm không được phép để trống")
    @Min(value = 0, message = "Điểm phải lớn 0")
    @Max(value = 10, message = "Điểm phải nhỏ hơn 10")
    Double score;
    @NotBlank(message = "Địa chỉ không được phép để trống")
    String address;
    @Valid
    @NotNull(message = "Lớp không được để trống")
    ClazzRequest clazz;

    @NotNull(message = "Ngày sinh là bắt buộc")
    @DobConstraint(min = 18, message = "Bạn tuổi")
    private LocalDate dateOfBirth;
}
