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

        // :)
        try {
            // Declaring everything as final is a tick of mine :)
            // Doesn't need to be done.
            // But declaring everything as restrictive as possible has advantages,
            // gives you more insights about your assumptions.
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] hashBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            /*
            e.printStackTrace();
            return null;
            */
            // Never use e.printStackTrace();
            // Exceptions are printed (or logged if you use a looging framework) automatically.
            // If you must to catch a checked exception as it is the case here,
            // simply rethrow it as a RuntimeException and add some context information:
            throw new RuntimeException("UserService.hashPassword - Exception", e);
        }
    }

    public void createUser(String username, String password, int benutzeralter, String email) {

        final User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setBenutzeralter(benutzeralter);
        newUser.setEmail(email);

        userRepository.saveUser(username, password, benutzeralter, email);
    }

}
