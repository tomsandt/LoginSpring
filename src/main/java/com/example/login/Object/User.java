package com.example.login.Object;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class User {

    @Id
    @Column(nullable = false, unique = true, length = 255)
    public String username;
    @Column(nullable = false)
    public String password;
    @Column(nullable = false)
    public LocalDate birthDate;
    @Column(nullable = false)
    public String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
