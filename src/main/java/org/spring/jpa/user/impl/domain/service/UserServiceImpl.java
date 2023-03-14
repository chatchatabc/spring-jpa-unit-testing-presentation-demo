package org.spring.jpa.user.impl.domain.service;

import org.spring.jpa.user.application.web.security.CodecUtils;
import org.spring.jpa.user.domain.error.UserAlreadyExistException;
import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;
import org.spring.jpa.user.domain.repository.UserRepo;
import org.spring.jpa.user.domain.service.UserService;
import org.spring.jpa.user.domain.specification.UserSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final UserRepo userRepo;

    final CodecUtils codecUtils;

    final UserSpec userSpec;

    @Autowired
    private UserServiceImpl(UserRepo userRepo, CodecUtils codecUtils, UserSpec userSpec) {
        this.userRepo = userRepo;
        this.codecUtils = codecUtils;
        this.userSpec = userSpec;
    }

    public User authUser(String email, String password) throws UserNotFoundException {
        return userRepo.findByEmail(email)
                .filter(user -> codecUtils.matches(password, user.getPassword(), user.getSalt()))
                .orElseThrow(() -> new UserNotFoundException("User not found: " + email));
    }

    public User registerUser(User user) throws UserAlreadyExistException {

        if(userSpec.isEmailExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists: " + user.getEmail());
        }else{
            return userRepo.save(user);
        }

    }

    @Override
    public Long mockMethod(User user) {
        return 2000L;
    }


}
