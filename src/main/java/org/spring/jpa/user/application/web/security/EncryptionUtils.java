package org.spring.jpa.user.application.web.security;

public interface EncryptionUtils {

    String encrypt(String password);

    Boolean matches(String password, String encodedPassword, String salt);
}
