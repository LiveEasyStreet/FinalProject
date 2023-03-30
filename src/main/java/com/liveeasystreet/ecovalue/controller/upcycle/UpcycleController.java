package com.liveeasystreet.ecovalue.controller.upcycle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UpcycleController {
    @GetMapping("/upcycleInfo")
    public String upcycle_info(){
        return"ecovalue/upcycle/upcycle_info";
    }
}
