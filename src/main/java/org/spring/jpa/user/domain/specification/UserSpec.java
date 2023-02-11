package org.spring.jpa.user.domain.specification;

public interface UserSpec {
    /**
     * check if user exists
     */
    Boolean isEmailExist(String email);
}
