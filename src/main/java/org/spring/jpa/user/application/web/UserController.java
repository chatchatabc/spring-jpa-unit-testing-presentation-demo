package org.spring.jpa.user.application.web;

import org.spring.jpa.user.application.common.vo.UserRegistrationVO;
import org.spring.jpa.user.application.web.security.EncryptionUtils;
import org.spring.jpa.user.domain.error.UserAlreadyExistException;
import org.spring.jpa.user.domain.error.UserNotFoundException;
import org.spring.jpa.user.domain.model.User;
import org.spring.jpa.user.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final EncryptionUtils encryptionUtils;

    @Autowired
    public UserController(UserService userService, EncryptionUtils encryptionUtils) {
        this.encryptionUtils = encryptionUtils;
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
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationVO());
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegistrationVO userRegistrationVO) {
        try {
            if(userRegistrationVO.getPassword().equals(userRegistrationVO.getMatchingPassword())){
                final String salt = encryptionUtils.generateSalt();
                User user = new User();
                user.setEmail(userRegistrationVO.getEmail());
                user.setPassword(encryptionUtils.encrypt(userRegistrationVO.getPassword(), salt));
                user.setSalt(salt);
                user.setUsername(userRegistrationVO.getUsername());
                userService.registerUser(user);
            }else{
                return "registration";
            }
            return "homepage";
        } catch (UserAlreadyExistException e) {
            return "registration";
        }
    }

}
