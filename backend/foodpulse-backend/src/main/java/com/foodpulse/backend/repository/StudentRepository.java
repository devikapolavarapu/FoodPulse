package com.foodpulse.backend.repository;

import com.foodpulse.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByEmail(String email);

    @Query("SELECT COUNT(s) FROM Student s")
    long countTotalStudents();

}