package org.spring.jpa.user.domain.service;

import org.spring.jpa.user.domain.error.UserAlreadyExistException;
import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;

public interface UserService {
    User authUser(String email, String password) throws UserNotFoundException;

    User registerUser(User user) throws UserAlreadyExistException;

    @SuppressWarnings("SameReturnValue")
    Long mockMethod(User user);
}
