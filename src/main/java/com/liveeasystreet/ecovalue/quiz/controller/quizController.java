package com.liveeasystreet.ecovalue.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/quiz")
@Controller
public class quizController {

    @RequestMapping
    public String quizStarter() {
        return "/quiz/QuizMain";
    }

    @RequestMapping("/question")
    public String question() {
        return "/quiz/QuizQuestion";
    }

    @RequestMapping("/addQuiz")
    public String quizCreate() {
        return "/quiz/QuizCreate";
    }

}
