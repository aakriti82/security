package com.securenotes.securenotes.repository;



import com.securenotes.securenotes.model.Note;
import com.securenotes.securenotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
}
