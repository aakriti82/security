package com.securenotes.securenotes.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @JsonIgnore
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

    // --- Utility ---
    @Transient
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }
}
