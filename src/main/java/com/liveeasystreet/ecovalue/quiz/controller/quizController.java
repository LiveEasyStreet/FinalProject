package com.liveeasystreet.ecovalue.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class quizController {

    @RequestMapping("/quiz")
    public String quizStarter() {
        return "/quiz/QuizMain";
    }

    @RequestMapping("/question")
    public String question() {
        return "/quiz/QuizQuestion";
    }
}
