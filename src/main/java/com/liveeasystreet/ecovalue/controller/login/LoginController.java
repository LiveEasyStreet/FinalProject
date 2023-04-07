package com.liveeasystreet.ecovalue.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String join() {

        return "ecovalue/login/login";
    }

}
