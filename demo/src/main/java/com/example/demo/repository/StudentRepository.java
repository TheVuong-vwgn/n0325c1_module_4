package com.example.demo.repository;

import com.example.demo.model.Student;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentRepository implements IStudentRepository {
    List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Thịnh", 9.6, "DN"),
                    new Student(2, "Điệp", 9.7, "DN"),
                    new Student(3, "Bảo", 9.8, "DN")
            )
    );

    public List<Student> findAll() {
        return students;
    }

    public Student save(Student student) {
        student.setId((int) (Math.random() * 1000000));
        students.add(student);
        return student;
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
