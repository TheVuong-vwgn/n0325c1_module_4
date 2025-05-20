package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    Student save (Student student);

    Student findById(int id);
}
