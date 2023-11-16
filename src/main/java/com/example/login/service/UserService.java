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

    public void createUser(UserDTO UserDTO) {

        User newUser = new User();

        newUser.setUsername(UserDTO.getUsername());
        newUser.setPassword(UserDTO.getPassword());
        newUser.setBirthDate(UserDTO.getBirthDate());
        newUser.setEmail(UserDTO.getEmail());

        userRepository.saveUser(newUser.getUsername(), newUser.getPassword(), newUser.getBirthDate(), newUser.getEmail());
    }
}
