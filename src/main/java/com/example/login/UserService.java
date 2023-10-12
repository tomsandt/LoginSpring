package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    public void createUser(String username, String password, LocalDate geburtsdatum, String email) {

        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setGeburtdatum(geburtsdatum);
        newUser.setEmail(email);

        userRepository.save(newUser);
    }

}