package org.spring.jpa.user.util;

public interface EncryptionUtils {

    String encrypt(String password);

    Boolean matches(String password, String encodedPassword, String salt);
}
