package com.example.student.controller;


import com.example.student.api.Registration;
import com.example.student.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@CrossOrigin(origins = "*")
public class RegisterController {

   @Autowired
   private RegisterService registerService;

    @PostMapping("/registerDetails")
    public Registration Student(@RequestBody Registration registration){
        return registerService.saveRegistration(registration);

    }
}
