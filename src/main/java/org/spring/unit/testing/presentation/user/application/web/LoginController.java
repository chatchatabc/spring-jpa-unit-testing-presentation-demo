package org.spring.unit.testing.presentation.user.application.web;

import org.spring.unit.testing.presentation.user.domain.model.User;
import org.spring.unit.testing.presentation.user.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password) {
        User result = userService.authUser(email, password);
        if (result != null) {
            return "homepage";
        } else {
            return "login";
        }
    }

}
