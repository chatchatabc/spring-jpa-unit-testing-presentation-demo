package org.spring.unit.testing.presentation.user.domain.service;

import org.spring.unit.testing.presentation.user.domain.model.User;

public interface UserService {
    User authUser(String email, String password);

    String registerUser(User user);
}
