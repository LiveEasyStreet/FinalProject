package com.liveeasystreet.ecovalue.controller.recycle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/recycle")
public class RecycleController {

    @GetMapping("/info")
    public String recycle_info(){
        return "ecovalue/recycle/recycle-info";
    }
}
