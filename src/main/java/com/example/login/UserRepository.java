package com.example.login;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user(username, password, geburtsdatum, email) VALUES (:username, :password, :geburtsdatum, :email)", nativeQuery = true)
    void saveUser(String username, String password, LocalDate geburtsdatum, String email);




}
