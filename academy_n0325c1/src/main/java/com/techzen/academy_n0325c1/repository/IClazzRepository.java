package com.techzen.academy_n0325c1.repository;

import com.techzen.academy_n0325c1.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClazzRepository extends JpaRepository<Clazz, Integer> {
    Integer id(int id);
//    @Query(value = """
//    SELECT * FROM Clazz
//    """, nativeQuery = true)
//    List<Clazz> findByIdAndName(@Param("id") int id, @Param("name") String name);
}

