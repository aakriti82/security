package com.securenotes.securenotes.controller;

import com.securenotes.securenotes.model.User;
import com.securenotes.securenotes.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginData) {
        var user = userRepo.findByUsername(loginData.getUsername()).orElse(null);
        if (user == null || !encoder.matches(loginData.getPassword(), user.getPassword()))
            return "Invalid credentials";

        return "Login successful";
    }
}
