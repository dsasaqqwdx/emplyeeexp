package com.example.employeeservice.controller;

import com.example.employeeservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username) {
        // In real use: validate username + password with DB
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }
}
