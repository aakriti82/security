package com.securenotes.securenotes;

import com.securenotes.securenotes.model.User;
import com.securenotes.securenotes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SecurenotesApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        // Basic context load test
    }

    @Test
    void adminUserShouldExist() {
        assertThat(userRepository.findByUsername("admin")).isPresent();
    }

    @Test
    void demoUserShouldExist() {
        assertThat(userRepository.findByUsername("demo")).isPresent();
    }


