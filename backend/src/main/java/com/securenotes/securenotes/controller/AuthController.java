package com.securenotes.securenotes.controller;

import com.securenotes.securenotes.model.User;
import com.securenotes.securenotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            Map<String, String> resp = new HashMap<>();
            resp.put("message", "Username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resp);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "User registered");
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        var user = userRepo.findByUsername(loginData.getUsername()).orElse(null);
        Map<String, String> resp = new HashMap<>();
        if (user == null || !encoder.matches(loginData.getPassword(), user.getPassword())) {
            resp.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
        }
        // For demonstration, return a fake JWT token (replace with real JWT in production)
        String fakeToken = "fake-jwt-token-for-" + user.getUsername();
        resp.put("message", "Login successful");
        resp.put("token", fakeToken);
        return ResponseEntity.ok(resp);
    }
}
