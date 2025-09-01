package com.example.student.controller;

import com.example.student.api.student;

import com.example.student.service.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teacher")  // Consistent base path
public class teachercontroller {

    @Autowired
    private teacherService studentService;

    @PostMapping("/addstudent/{registrationId}")
    public student createStudent(@PathVariable int registrationId, @RequestBody student studentDetails) {
        return studentService. addStudent(registrationId, studentDetails);
    }
    @GetMapping("/getbyid/{rollno}")
    public Optional<student> getStudent(@PathVariable int rollno) {

        return studentService.GetStudent(rollno);
    }

    @GetMapping("/viewall")
    public Iterable<student> getAllStudents() {

        return studentService.getAll();
    }

    @PutMapping("/update/{rollno}")
    public student updateStudent(@PathVariable int rollno, @RequestBody student updated) {

        return studentService.update(rollno,updated);
    }

    @DeleteMapping("/del/{rollno}")
    public ResponseEntity<String> deleteStudent(@PathVariable int rollno) {

         boolean delete=studentService.DeleteStudent(rollno);
         if(delete) {
             return ResponseEntity.ok("sucessfully deleted" +rollno);
         }else{
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("student not found");

         }
    }
}