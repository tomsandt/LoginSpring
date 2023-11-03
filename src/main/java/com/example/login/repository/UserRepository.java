package com.example.login.repository;

import com.example.login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user(username, password, birth_date, email) VALUES (:username, :password, :birthDate, :email)", nativeQuery = true)
    void saveUser(@Param("username") String username, @Param("password") String password, @Param("birthDate") LocalDate birthDate, @Param("email") String email);

}
