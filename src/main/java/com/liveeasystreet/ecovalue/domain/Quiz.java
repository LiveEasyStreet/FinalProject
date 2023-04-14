package com.liveeasystreet.ecovalue.domain;

import lombok.*;

import java.time.LocalDate;

@Data
public class Quiz {

    private Long quizId; //퀴즈번호
    private String category; //카테고리 (DTO)
    private String title; //퀴즈제목 (DTO)
    private String detail; //퀴즈내용 (DTO)
    private String solve; //해설 (DTO)
    private LocalDate date; //등록날짜
    private Boolean answer; //정답 (DTO)
    private Integer occurredProblemCount; //나온횟수
    private Integer numberOfHits; //맞춘횟수

    public Quiz() {
        this.occurredProblemCount = 0;
        this.numberOfHits = 0;
    }

    public Quiz(String category, String title, String detail, String solve, Boolean answer) {
        this();
        this.setCategory(category);
        this.setTitle(title);
        this.setDetail(detail);
        this.setSolve(solve);
        this.setAnswer(answer);
        this.setDate(LocalDate.now());
    }


}


