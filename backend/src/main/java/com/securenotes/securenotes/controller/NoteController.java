package com.securenotes.securenotes.controller;

import com.securenotes.securenotes.model.Note;
import com.securenotes.securenotes.repository.NoteRepository;
import com.securenotes.securenotes.repository.UserRepository;
import com.securenotes.securenotes.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired private NoteRepository noteRepo;
    @Autowired private UserRepository userRepo;

    // Simulate authentication by extracting username from a fake JWT token in the Authorization header
    private String getUsernameFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer fake-jwt-token-for-")) return null;
        return authHeader.substring("Bearer fake-jwt-token-for-".length());
    }

    @GetMapping
    public ResponseEntity<?> getUserNotes(@RequestHeader("Authorization") String authHeader) {
        String username = getUsernameFromToken(authHeader);
        if (username == null) {
            Map<String, String> resp = new HashMap<>();
            resp.put("message", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
        }
        List<Note> notes = noteRepo.findByUsername(username);
        Map<String, Object> resp = new HashMap<>();
        resp.put("notes", notes.stream().map(Note::getContent).toList());
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<?> addNote(@RequestHeader("Authorization") String authHeader, @RequestBody Map<String, String> body) {
        String username = getUsernameFromToken(authHeader);
        if (username == null) {
            Map<String, String> resp = new HashMap<>();
            resp.put("message", "Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
        }
        String content = body.get("note");
        if (content == null || content.trim().isEmpty()) {
            Map<String, String> resp = new HashMap<>();
            resp.put("message", "Note content required");
            return ResponseEntity.badRequest().body(resp);
        }
        Note note = new Note();
        note.setUsername(username);
        note.setContent(content);
        noteRepo.save(note);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Note saved");
        return ResponseEntity.ok(resp);
    }
}