package com.example.student.controller;

import com.example.student.api.Registration;
import com.example.student.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class loginController {

    @Autowired
    private loginService service;

    @PostMapping("/loginDetails")
    public Map<String, String> login(@RequestBody Registration registration) {
        // Extract username and password
        String username = registration.getUsername();
        String password = registration.getPassword();

        // Verify credentials and generate token
        String token = service.verify(username, password);

        // Return token in JSON format
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
