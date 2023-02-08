package org.spring.jpa.user.domain.specification;

public interface UserSpec {
    /**
     * check if user exists
     */
    Boolean isUserExist(String email);
}
