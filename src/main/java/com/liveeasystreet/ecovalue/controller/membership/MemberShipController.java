package com.liveeasystreet.ecovalue.controller.membership;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/membership")
public class MemberShipController {

    @GetMapping
    public String membershipHome(){
        return "membership/membership";
    }

}
