package com.liveeasystreet.ecovalue.controller.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class QuizResultData {
        private Map<Long, Boolean> data;
        private Map<Long, Quiz> map;
        private int score;
}
