package com.techzen.academy_n0325c1.service;

import com.techzen.academy_n0325c1.model.Student;
import com.techzen.academy_n0325c1.repository.IStudentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService implements IStudentService {
    IStudentRepository studentRepository;

    public Page<Student> findByAttr(String name, Double fromScore, Double toScore, Pageable pageable) {
        return studentRepository.findByAttr(name, fromScore, toScore, pageable);
    }
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(int id) {
        return studentRepository.findById(id).orElse(null);
    }
}
