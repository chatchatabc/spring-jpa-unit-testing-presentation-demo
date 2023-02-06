package org.spring.jpa.user.impl.util;

import org.spring.jpa.user.util.PassCipher;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class PassCipherImpl implements PassCipher {

    private static final int SALT_LENGTH = 32;

    public String encrypt(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((password + Arrays.toString(saltGenerator())).getBytes());
            byte[] hash = messageDigest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public Boolean matches(String password, String encodedPassword, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((password + ":" + salt).getBytes());
            byte[] hash = messageDigest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString().equals(encodedPassword);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static byte[] saltGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }

}
