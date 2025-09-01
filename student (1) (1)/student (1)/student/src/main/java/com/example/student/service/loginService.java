package com.example.student.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class loginService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final MyUserDetailsService userDetailsService;

    public loginService(AuthenticationManager authenticationManager, JWTService jwtService,
                        MyUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public String verify(String username, String password) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Generate JWT token
            return jwtService.generateToken(userDetails);

        } catch (Exception e) {
            return "Login failed: " + e.getMessage();
        }
    }
}
