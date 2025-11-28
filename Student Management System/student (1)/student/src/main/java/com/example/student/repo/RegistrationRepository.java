package com.example.student.repo;

import com.example.student.api.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Integer> {
    Registration findByUsername(String username);

}
