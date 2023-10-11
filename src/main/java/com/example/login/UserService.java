package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Service
public abstract class UserService implements JpaRepository<User, Long> {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public static String hashPassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

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
