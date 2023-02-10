package org.spring.jpa.user.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("SameReturnValue")
@Controller
public class HomeController {

    @GetMapping("/home")
    public String homepage() {
        return "homepage";
    }



}
