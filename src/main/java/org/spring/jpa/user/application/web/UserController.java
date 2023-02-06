package org.spring.jpa.user.application.web;

import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;
import org.spring.jpa.user.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password, Model model) {
        try {
            final User user = userService.authUser(email, password);
            model.addAttribute("user", user);
            return "homepage";
        } catch (UserNotFoundException e) {
            return "login";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "login";
    }

    @PostMapping("/register")
    public String register(String email, String password, Model model) {
        try {
            final User user = userService.authUser(email, password);
            model.addAttribute("user", user);
            return "homepage";
        } catch (UserNotFoundException e) {
            return "login";
        }
    }

}
