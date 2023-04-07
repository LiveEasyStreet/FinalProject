package com.liveeasystreet.ecovalue.controller.forgotpassword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class forgotPasswordController {

    @GetMapping("/forgot-password")
    public String forgotPassword() {

        return "ecovalue/forgot-password/forgot-password";
    }
}
