package org.spring.jpa.user.impl.domain.specification;

import org.spring.jpa.user.domain.repository.UserRepo;
import org.spring.jpa.user.domain.specification.UserSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSpecImpl implements UserSpec {

    UserRepo userRepo;

    @Autowired
    public UserSpecImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * check if user exists implementation
     */
    public Boolean isUserExist(String email) {
        return userRepo.findByEmail(email).isPresent();
    }
}
