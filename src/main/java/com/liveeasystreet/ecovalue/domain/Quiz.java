package com.liveeasystreet.ecovalue.domain;

import lombok.*;

import java.time.LocalDate;

@Data
public class Quiz {

    private Long Id;
    private String category;
    private String title;
    private String detail;
    private String solve;
    private LocalDate date;
    private Boolean answer;
    private Integer occurredProblemCount;
    private Integer numberOfHits;

    public static Quiz createQuiz(String category, String title, String detail, String solve, boolean answer) {
        Quiz quiz = new Quiz();
        quiz.setCategory(category);
        quiz.setTitle(title);
        quiz.setDetail(detail);
        quiz.setSolve(solve);
        quiz.setAnswer(answer);
        quiz.setDate(LocalDate.now());
        quiz.setOccurredProblemCount(0);
        quiz.setNumberOfHits(0);

        return quiz;
    }

}
