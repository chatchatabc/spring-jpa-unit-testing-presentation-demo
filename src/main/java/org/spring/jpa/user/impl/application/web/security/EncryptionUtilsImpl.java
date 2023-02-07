package org.spring.jpa.user.impl.application.web.security;

import org.spring.jpa.user.application.web.security.EncryptionUtils;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.SecureRandom;

@Service
public class EncryptionUtilsImpl implements EncryptionUtils {

    private static final int SALT_LENGTH = 6;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static final SecureRandom random = new SecureRandom();

    public String encrypt(String password, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((password  + ":" + salt).getBytes());
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
            return encrypt(password, salt).equals(encodedPassword);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public String generateSalt() {
        if (SALT_LENGTH < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(SALT_LENGTH);
        for (int i = 0; i < SALT_LENGTH; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }

        return sb.toString();
    }


}
