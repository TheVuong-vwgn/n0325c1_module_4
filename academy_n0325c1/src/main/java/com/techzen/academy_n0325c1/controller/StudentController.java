package com.techzen.academy_n0325c1.controller;

import com.techzen.academy_n0325c1.dto.ApiResponse;
import com.techzen.academy_n0325c1.dto.clazz.ClazzResponse;
import com.techzen.academy_n0325c1.dto.page.PageResponse;
import com.techzen.academy_n0325c1.dto.student.StudentRequest;
import com.techzen.academy_n0325c1.dto.student.StudentResponse;
import com.techzen.academy_n0325c1.exception.AppException;
import com.techzen.academy_n0325c1.exception.ErrorCode;
import com.techzen.academy_n0325c1.mapper.IStudentMapper;
import com.techzen.academy_n0325c1.model.Clazz;
import com.techzen.academy_n0325c1.model.Student;
import com.techzen.academy_n0325c1.service.IClazzService;
import com.techzen.academy_n0325c1.service.IStudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@AllArgsConstructor // Constructor đây nè!
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    // new: Tạo ra đối tượng => Lập trình viên phải chủ động quản lý đối tượng
    // Bean: Đối tượng => Spring tạo ra và quản lý
    IStudentService studentService; // DI thông qua Constructor
    IClazzService clazzService;
    IStudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<StudentResponse>>> getStudents(
            @RequestParam(defaultValue = "") String name,
            Double fromScore, Double toScore, Pageable pageable
    ) {
        return ResponseEntity.ok(ApiResponse.<PageResponse<StudentResponse>>builder()
                .data(new PageResponse<>(studentService.findByAttr(name, fromScore, toScore, pageable)
                        .map(studentMapper::studentToStudentResponse)))
                .build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody StudentRequest studentRequest) {

        //B1: Chuyển từ StudentRequest sang Entity
        Student student = studentMapper.studentReStudentToStudent(studentRequest);

        //B2: Lưu Entity xuống DB
        student = studentService.save(student);
        if(studentRequest.getClazz() != null) {
            student.setClazz(clazzService.findById(studentRequest.getClazz().getId()));
        }

        //B3: Chuyển Entity về StudentRepsponse
        StudentResponse studentResponse = studentMapper.studentToStudentResponse(student);
        //B4: Return StudentResponse
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<StudentResponse>builder()
                        .data(studentResponse)
                        .build()

        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudent(@PathVariable("id") int id) {
        Student student = studentService.findById(id);

        if (student == null) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }

        return ResponseEntity.ok(ApiResponse.<StudentResponse>builder()
                .data(studentMapper.studentToStudentResponse(student))
                .build());
    }
}
