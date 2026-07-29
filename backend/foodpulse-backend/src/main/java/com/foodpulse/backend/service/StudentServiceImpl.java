package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.StudentDTO;
import com.foodpulse.backend.entity.Student;
import com.foodpulse.backend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for Student management.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        log.info("Registering student with email: {}", studentDTO.getEmail());
        
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        student.setHostel(studentDTO.getHostel());

        Student saved = studentRepository.save(student);

        StudentDTO response = new StudentDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setHostel(saved.getHostel());
        return response;
    }

    @Override
    public String loginStudent(StudentDTO studentDTO) {
        log.info("Processing login request for email: {}", studentDTO.getEmail());
        
        Optional<Student> existing = studentRepository.findByEmail(studentDTO.getEmail());

        if (existing.isPresent()) {
            Student s = existing.get();
            if (s.getPassword().equals(studentDTO.getPassword())) {
                log.info("Login successful for email: {}", studentDTO.getEmail());
                return "Login Success";
            }
        }

        log.warn("Login failed for email: {}", studentDTO.getEmail());
        return "Invalid Email or Password";
    }
}
