package com.example.student.repo;

import com.example.student.api.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface studentRepository extends JpaRepository<student,Integer> {
}
