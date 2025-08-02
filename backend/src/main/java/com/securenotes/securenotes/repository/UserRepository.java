package com.securenotes.securenotes.repository;

import com.securenotes.securenotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // Find all users with a specific role
    List<User> findByRole(String role);

    // Find locked users
    List<User> findByLockedTrue();

    // Custom query: search users by username (case-insensitive, partial match)
    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :usernamePart, '%'))")
    List<User> searchByUsername(@Param("usernamePart") String usernamePart);

    // Count users by role
    long countByRole(String role);

    // Find users with failed login attempts above a threshold
    List<User> findByFailedLoginAttemptsGreaterThan(int threshold);

    // Find all unlocked users
    List<User> findByLockedFalse();

    // Custom query: get usernames of all users
    @Query("SELECT u.username FROM User u")
    List<String> findAllUsernames();

    // Custom query: get users created after a certain id (for pagination or sync)
    @Query("SELECT u FROM User u WHERE u.id > :afterId")
    List<User> findAllAfterId(@Param("afterId")
