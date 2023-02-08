package org.spring.jpa.user.application.web.security;

public interface CodecUtils {

    String generateSalt();

    String hash(String password, String salt);

    Boolean matches(String password, String hashedPass, String salt);
}
