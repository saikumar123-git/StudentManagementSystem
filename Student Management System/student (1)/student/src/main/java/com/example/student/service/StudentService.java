package com.example.student.service;

import com.example.student.api.student;
import com.example.student.repo.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private studentRepository repo;

    public Optional<student> getDetails(Integer id) {
         return repo.findById(id);
    }
}
