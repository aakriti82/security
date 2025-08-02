package com.securenotes.securenotes.controller;

import com.securenotes.securenotes.model.Note;
import com.securenotes.securenotes.repository.NoteRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired private NoteRepository noteRepo;

    @GetMapping("/")
    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }

    @PostMapping("/")
    public String addNote(@RequestBody Note note) {
        noteRepo.save(note);
        return "Note saved";
    }
}
