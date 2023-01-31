package org.spring.jpa.user.impl.domain.service;

import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;
import org.spring.jpa.user.domain.repository.UserRepo;
import org.spring.jpa.user.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;

    @Autowired
    private UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User authUser(String email, String password) throws UserNotFoundException {
        return userRepo.findByEmail(email)
                .map(user -> user.getPassword().equals(password) ? user : null)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + email));
       /* if (result.isPresent()) {
            User user = result.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        } else {
            throw new UserNotFoundException("User not found: " + email);
        }
        return null;*/
    }

    public Long registerUser(User user) {
        userRepo.save(user);
        return user.getId();
    }
}
