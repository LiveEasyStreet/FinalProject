package com.liveeasystreet.ecovalue.repository.quiz;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizSearchCond {
    private Long quizId;
    private String category;
    private String title;
    private String detail;

    public QuizSearchCond(String category, String title, String detail) {
        this.quizId=null;
        this.category = category;
        this.title = title;
        this.detail = detail;
    }

    public QuizSearchCond(Long id, String category, String title, String detail) {
        this.quizId = id;
        this.category = category;
        this.title = title;
        this.detail = detail;
    }

}
