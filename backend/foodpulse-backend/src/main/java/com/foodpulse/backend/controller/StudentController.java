package com.foodpulse.backend.controller;

import com.foodpulse.backend.dto.StudentDTO;
import com.foodpulse.backend.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller exposing endpoints for student registration and login operations.
 */
@RestController
@RequestMapping("/students")
@CrossOrigin
@Tag(name = "Student Controller", description = "Endpoints for student registration and login operations")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Registers a new student in the system.
     *
     * @param studentDTO the student registration details
     * @return the registered student details
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new student", description = "Saves student details in the database and returns the details.")
    public ResponseEntity<StudentDTO> registerStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO registered = studentService.registerStudent(studentDTO);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    /**
     * Authenticates a student based on email and password.
     *
     * @param studentDTO the student login credentials
     * @return message indicating login status
     */
    @PostMapping("/login")
    @Operation(summary = "Authenticate student login", description = "Verifies student credentials and returns a status message.")
    public ResponseEntity<String> loginStudent(@RequestBody StudentDTO studentDTO) {
        String result = studentService.loginStudent(studentDTO);
        if ("Login Success".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
    }
}