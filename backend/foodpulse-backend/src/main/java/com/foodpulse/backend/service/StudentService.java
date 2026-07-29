package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.StudentDTO;

/**
 * Service interface for managing students.
 */
public interface StudentService {

    /**
     * Registers a new student.
     *
     * @param studentDTO the student details DTO
     * @return the registered student DTO
     */
    StudentDTO registerStudent(StudentDTO studentDTO);

    /**
     * Logs in a student.
     *
     * @param studentDTO the student credentials DTO
     * @return login result message
     */
    String loginStudent(StudentDTO studentDTO);
}
