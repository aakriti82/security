package com.securenotes.securenotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.securenotes.securenotes.model.User;
import com.securenotes.securenotes.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurenotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurenotesApplication.class, args);
    }

    // Optional: Seed an admin user for testing/demo purposes
    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setLocked(false);
                admin.setFailedLoginAttempts(0);
                userRepo.save(admin);
                System.out.println("Admin user created: admin/admin123");
            }
        };
    }
}
