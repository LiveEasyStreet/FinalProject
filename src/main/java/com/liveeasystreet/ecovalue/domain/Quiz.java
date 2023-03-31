package com.liveeasystreet.ecovalue.domain;

import lombok.*;

import java.time.LocalDate;

@Data
public class Quiz {

    private Long Id; //퀴즈번호
    private String category; //카테고리 (DTO)
    private String title; //퀴즈제목 (DTO)
    private String detail; //퀴즈내용 (DTO)
    private String solve; //해설 (DTO)
    private LocalDate date; //등록날짜
    private Boolean answer; //정답 (DTO)
    private Integer occurredProblemCount; //나온횟수
    private Integer numberOfHits; //맞춘횟수

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
