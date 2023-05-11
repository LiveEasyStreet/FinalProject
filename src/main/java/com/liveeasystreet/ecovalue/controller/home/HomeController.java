package com.liveeasystreet.ecovalue.controller.home;

import com.liveeasystreet.ecovalue.controller.login.SessionConst;
import com.liveeasystreet.ecovalue.dto.member.MemberSessionDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "ecovalue/home/ecovalue-home";
    }
}
