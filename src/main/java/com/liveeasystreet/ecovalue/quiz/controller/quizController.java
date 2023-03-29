package com.liveeasystreet.ecovalue.quiz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/quiz")
@Controller
@Slf4j
public class quizController {

    @RequestMapping
    public String quizStarter() {
        log.info("quizStarter invoked");
        return "/quiz/QuizMain";
    }

    @RequestMapping("/question")
    public String question() {
        log.info("question invoked");
        return "/quiz/QuizQuestion";
    }

    @RequestMapping("/addQuiz")
    public String quizCreate() {
        log.info("quizCreate invoked");
        return "/quiz/QuizCreate";
    }

    @RequestMapping("/score")
    public String score() {
        log.info("score invoked");
        return "/quiz/Score";
    }

}
