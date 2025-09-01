package com.example.student.service;

import com.example.student.api.Registration;
import com.example.student.repo.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class RegisterService {


        @Autowired
        private RegistrationRepository registrationRepository;

        private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

        public Registration saveRegistration(Registration registration) {
            registration.setPassword(encoder.encode(registration.getPassword()));
            return registrationRepository.save(registration);
        }
    }

