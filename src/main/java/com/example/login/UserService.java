package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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


    public void createUser(String username, String password, int benutzeralter, String email) {

        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setBenutzeralter(benutzeralter);
        newUser.setEmail(email);

        userRepository.saveUser(username, password, benutzeralter, email);
    }

}