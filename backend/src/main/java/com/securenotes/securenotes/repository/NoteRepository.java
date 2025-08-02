package com.securenotes.securenotes.repository;

import com.securenotes.securenotes.model.Note;
import com.securenotes.securenotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);

    // Find all notes for a user by username (useful for REST APIs)
    @Query("SELECT n FROM Note n WHERE n.user.username = :username")
    List<Note> findByUsername(String username);

    // Find notes containing a keyword for a user
    @Query("SELECT n FROM Note n WHERE n.user = :user AND n.content LIKE %:keyword%")
    List<Note> findByUserAndContentContaining(User user, String keyword);
}
