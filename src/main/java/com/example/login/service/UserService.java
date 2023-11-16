package com.example.login.service;

import com.example.login.domain.User;
import com.example.login.domain.UserDTO;
import com.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void createUser(UserDTO userTO) {

        User newUser = new User();

        newUser.setUsername(userTO.getUsername());
        newUser.setPassword(userTO.getPassword());
        newUser.setBirthDate(userTO.getBirthDate());
        newUser.setEmail(userTO.getEmail());

        userRepository.saveUser(newUser.getUsername(), newUser.getPassword(), newUser.getBirthDate(), newUser.getEmail());
    }
}
