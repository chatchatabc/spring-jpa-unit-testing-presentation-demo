package org.spring.jpa.user.domain.service;

import org.spring.jpa.user.domain.model.User;

public interface UserService {
    User authUser(String email, String password);

    String registerUser(User user);
}
