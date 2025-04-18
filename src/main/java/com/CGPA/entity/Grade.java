package com.CGPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String subject;

    @Column
    private Double score;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Grade() {
    }

    public Grade(String subject, Double score, User user) {
        this.subject = subject;
        this.score = score;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }
}
