package com.techzen.academy_n0325c1.service;

import com.techzen.academy_n0325c1.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    Student save(Student student);

    Student findById(int id);

    Page<Student> findByAttr(String name, Double fromScore, Double toScore, Pageable pageable);
}
