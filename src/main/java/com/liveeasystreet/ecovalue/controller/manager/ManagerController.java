package com.liveeasystreet.ecovalue.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ManagerController {

    @GetMapping("/manager-home")
    public String home() {
        return "manager/manager-home";
    }
}
