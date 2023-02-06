package org.spring.jpa.user.impl.domain.service;

import org.spring.jpa.user.domain.error.UserAlreadyExistException;
import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;
import org.spring.jpa.user.domain.repository.UserRepo;
import org.spring.jpa.user.domain.service.UserService;
import org.spring.jpa.user.application.web.security.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;

    EncryptionUtils encryptionUtils;

    @Autowired
    private UserServiceImpl(UserRepo userRepo, EncryptionUtils encryptionUtils) {
        this.userRepo = userRepo;
        this.encryptionUtils = encryptionUtils;
    }

    public User authUser(String email, String password) throws UserNotFoundException {
        return userRepo.findByEmail(email)
                .map(user -> encryptionUtils.matches(password, user.getPassword(), user.getSalt()) ? user : null)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + email));
    }

    public Long registerUser(User user) throws UserAlreadyExistException {

        if(userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exists: " + user.getEmail());
        }else{
            return userRepo.save(user).getId();
        }

    }

    @Override
    public Long mockMethod(User user) {
        return 2000L;
    }


}
