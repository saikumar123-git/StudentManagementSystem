package com.example.student.service;

import com.example.student.api.student;
import com.example.student.repo.studentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class teacherService {


    @Autowired
    private studentRepository repository;

    public student addStudent(student student) {
        return repository.save(student);
    }

    public Optional<student> GetStudent(int rollno) {
        return repository.findById(rollno);
    }

    public Iterable<student> getAll() {
        return repository.findAll();
    }

    public student update(int rollno, student updated) {

        Optional<student> excistingStudent=repository.findById(rollno);

        if(!excistingStudent.isPresent()){
            throw new EntityNotFoundException("Student with rollno"+rollno+"is not present");
        }
        updated.setRollno(rollno);
        return repository.save(updated);
    }

    public boolean DeleteStudent(int rollno) {
         if(repository.existsById(rollno)) {
             repository.deleteById(rollno);
             return true;
         }
        return false;
    }
}
