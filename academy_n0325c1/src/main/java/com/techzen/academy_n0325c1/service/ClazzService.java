package com.techzen.academy_n0325c1.service;

import com.techzen.academy_n0325c1.model.Clazz;
import com.techzen.academy_n0325c1.model.Student;
import com.techzen.academy_n0325c1.repository.IClazzRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClazzService implements IClazzService {
    IClazzRepository clazzRepository;
    public Clazz findById(int id) {
        return clazzRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clazz not found #" + id));
    }

}
