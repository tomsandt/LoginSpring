package com.example.login.Service;

import com.example.login.Object.User;
import com.example.login.Object.UserTO;
import com.example.login.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Formatter;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public static String hashPassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            try (Formatter formatter = new Formatter(hexString)) {
                for(byte hashByte : hashBytes) {
                    formatter.format("%02x", hashByte);
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }



    public void createUser(UserTO userTO) {

        User newUser = new User();

        newUser.setUsername(userTO.getUsername());
        newUser.setPassword(userTO.getPassword());
        newUser.setBirthDate(userTO.getBirthDate());
        newUser.setEmail(userTO.getEmail());

        userRepository.saveUser(newUser.getUsername(), newUser.getPassword(), newUser.getBirthDate(), newUser.getEmail());
    }

}
