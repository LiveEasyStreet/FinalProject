package com.liveeasystreet.ecovalue.quiz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/quiz")
@Controller
@Slf4j
public class quizController {

    @GetMapping
    public String quizMain() {
        log.info("quizStarter invoked");
        return "/quiz/QuizMain";
    }

    @GetMapping("/question")
    public String question() {
        log.info("question invoked");
        return "/quiz/QuizQuestion";
    }

    @GetMapping("/score")
    public String score() {
        log.info("score invoked");
        return "/quiz/Score";
    }
}
