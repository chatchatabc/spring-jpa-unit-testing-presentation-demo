package org.spring.jpa.user.application.web.security;

public interface EncryptionUtils {

    String generateSalt();

    String encrypt(String password, String salt);

    Boolean matches(String password, String encodedPassword, String salt);
}
