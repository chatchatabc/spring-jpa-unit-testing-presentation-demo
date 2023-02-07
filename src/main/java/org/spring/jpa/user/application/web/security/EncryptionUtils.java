package org.spring.jpa.user.application.web.security;

public interface EncryptionUtils {

    String getSalt();

    String encrypt(String password, String salt);

    Boolean matches(String password, String encodedPassword, String salt);
}
