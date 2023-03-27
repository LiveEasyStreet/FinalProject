package com.liveeasystreet.ecovalue.quiz.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class quizController {

    @GetMapping
    public String quizStarter(){
        return "/templates/quiz/QuizMain";
    }

    @GetMapping("/")

}
