package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {
    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Trung", 5),
                    new Student(2, "Nguyen", 5),
                    new Student(3, "Hung", 5)
            )
    );

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getStudents() {
        return ResponseEntity.ok(ApiResponse.<List<Student>>builder()
                .data(students)
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student student){
        student.setId((int) (Math.random() * 1000000));
        students.add(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder()
                        .data(student)
                        .build()
        );
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return ResponseEntity.ok(ApiResponse.<Student>builder()
                        .data(student)
                        .build());
            }
        }
        throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
    }
}
