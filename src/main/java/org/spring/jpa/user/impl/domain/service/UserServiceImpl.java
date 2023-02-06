package org.spring.jpa.user.impl.domain.service;

import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;
import org.spring.jpa.user.domain.repository.UserRepo;
import org.spring.jpa.user.domain.service.UserService;
import org.spring.jpa.user.util.EncryptionUtils;
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
       /* if (result.isPresent()) {
            User user = result.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        } else {
            throw new UserNotFoundException("User not found: " + email);
        }
        return null; */
    }

    public Long registerUser(User user) {

        userRepo.save(user);
        return user.getId();
    }


}
