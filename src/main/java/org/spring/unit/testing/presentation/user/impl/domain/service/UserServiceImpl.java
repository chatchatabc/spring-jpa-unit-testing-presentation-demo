package org.spring.unit.testing.presentation.user.impl.domain.service;

import org.spring.unit.testing.presentation.user.domain.model.User;
import org.spring.unit.testing.presentation.user.domain.repository.UserRepo;
import org.spring.unit.testing.presentation.user.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    @Autowired
    private UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User authUser(String email, String password) throws RuntimeException {

        Optional<User> result = userRepo.findByEmail(email);
        if (result.isPresent()) {
            User user = result.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }else{
             throw new RuntimeException("User not found");
        }
        return null;
    }

    public String registerUser(User user) {
        return "";
    }
}
