package com.example.login.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Util {

    public String hashPassword(String password) {

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
}
