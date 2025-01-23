package com.Assignment.TODO.entity;

import com.Assignment.TODO.Dto.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @NotBlank(message = "Email is required")
    @Email(message = "invalid email format")
    @Column(nullable = false,unique = true)
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 3,max = 50,message = "username must be between 3-50 letters")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "password is required")
    @Size(min=8,message = "password has to be atleast 8 characters")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Todo> userTodos=new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(Long userid, String email, String username, String password, Role role, List<Todo> userTodos, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.userTodos = userTodos;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public @NotBlank(message = "Email is required") @Email(message = "invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "invalid email format") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Username is required") @Size(min = 3, max = 50, message = "username must be between 3-50 letters") String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(@NotBlank(message = "Username is required") @Size(min = 3, max = 50, message = "username must be between 3-50 letters") String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ role.name()));
    }

    public @NotBlank(message = "password is required") @Size(min = 8, message = "password has to be atleast 8 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "password is required") @Size(min = 8, message = "password has to be atleast 8 characters") String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Todo> getUserTodos() {
        return userTodos;
    }

    public void setUserTodos(List<Todo> userTodos) {
        this.userTodos = userTodos;
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
