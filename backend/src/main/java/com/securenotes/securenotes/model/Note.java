package com.securenotes.securenotes.model;

import jakarta.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    private User user;

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
