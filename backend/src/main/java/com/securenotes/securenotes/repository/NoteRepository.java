package com.securenotes.securenotes.repository;

import com.securenotes.securenotes.model.Note;
import com.securenotes.securenotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);

    // Find all notes for a user by username (useful for REST APIs)
    @Query("SELECT n FROM Note n WHERE n.user.username = :username")
    List<Note> findByUsername(@Param("username") String username);

    // Find notes containing a keyword for a user
    @Query("SELECT n FROM Note n WHERE n.user = :user AND n.content LIKE %:keyword%")
    List<Note> findByUserAndContentContaining(@Param("user") User user, @Param("keyword") String keyword);

    // Find a note by id and user (for secure access)
    Optional<Note> findByIdAndUser(Long id, User user);

    // Count notes for a user
    long countByUser(User user);

    // Delete all notes for a user
    void deleteByUser(User user);

    // Find notes created after a certain id for a user (pagination or sync)
    List<Note> findByUserAndIdGreaterThan(User user, Long id);
}
