package com.Assignment.TODO.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoid;
    @NotBlank(message = "tototitle is required")
    @Size(min = 1,max = 100,message = "title has to be between the 1-100 characters")
    private String todotitle;
    @Size(max = 500,message = "description only contains 500 characters")
    private String tododescription;

    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Todo() {
    }

    public Todo(Long todoid, String todotitle, String tododescription, boolean isCompleted, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.todoid = todoid;
        this.todotitle = todotitle;
        this.tododescription = tododescription;
        this.isCompleted = isCompleted;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getTodoid() {
        return todoid;
    }

    public void setTodoid(Long todoid) {
        this.todoid = todoid;
    }

    public @NotBlank(message = "tototitle is required") @Size(min = 1, max = 100, message = "title has to be between the 1-100 characters") String getTodotitle() {
        return todotitle;
    }

    public void setTodotitle(@NotBlank(message = "tototitle is required") @Size(min = 1, max = 100, message = "title has to be between the 1-100 characters") String todotitle) {
        this.todotitle = todotitle;
    }

    public @Size(max = 500, message = "description only contains 500 characters") String getTododescription() {
        return tododescription;
    }

    public void setTododescription(@Size(max = 500, message = "description only contains 500 characters") String tododescription) {
        this.tododescription = tododescription;
    }

    public boolean isComplete() {
        return isCompleted;
    }

    public void setComplete(boolean complete) {
        isCompleted = complete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
