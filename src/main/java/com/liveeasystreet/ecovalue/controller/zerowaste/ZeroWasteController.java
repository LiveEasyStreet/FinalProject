package com.liveeasystreet.ecovalue.controller.zerowaste;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zerowaste")
public class ZeroWasteController {

    @GetMapping("/introduce")
    public String introduce() {
        return "ecovalue/zerowaste/introduce";
    }

    @GetMapping("/challenge")
    public String getChallenge() {
        return "#";
    }

    @PostMapping("/challenge")
    public String postChallenge() {
        return "#";
    }
}
