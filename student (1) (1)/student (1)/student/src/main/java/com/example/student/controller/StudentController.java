package com.example.student.controller;

import com.example.student.api.student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * Securely fetches details for the *currently authenticated* student.
     * This endpoint uses the Authentication principal from the security context to identify the user,
     * making it impossible for a student to request another student's data.
     * @param authentication The security principal injected by Spring Security, containing the logged-in user's details.
     * @return A ResponseEntity containing the student's details or a 404 Not Found if their details do not exist.
     */
    @GetMapping("/me")
    public ResponseEntity<student> getMyDetails(Authentication authentication) {
        // The service layer will handle extracting the user's identity from the token.
        Optional<student> studentOptional = studentService.getDetailsForAuthenticatedUser(authentication);

        return studentOptional
                .map(ResponseEntity::ok) // If student is present, wrap in a 200 OK response.
                .orElseGet(() -> ResponseEntity.notFound().build()); // Otherwise, return 404.
    }
}