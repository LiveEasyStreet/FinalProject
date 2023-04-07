package com.liveeasystreet.ecovalue.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDto {

    private String category; //카테고리 !
    private String title; //퀴즈제목 !
    private String detail; //퀴즈내용 !
    private String solve; //해설 !
    private Boolean answer; //정답 !
}
