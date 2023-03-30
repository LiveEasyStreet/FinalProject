package com.liveeasystreet.ecovalue.quiz.service;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuizServiceImpl implements IQuizService {

    @Override
    public void resistQuiz(QuizDTO updateParam) {

    }

    @Override
    public void updateQuiz(Long id, QuizDTO updateParam) {

    }

    @Override
    public void deleteQuiz(Long id) {

    }

    @Override
    public List<Quiz> findAllQuiz() {
        return null;
    }

    @Override
    public Optional<Quiz> findQuiz(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Quiz> selectsQuiz() {
        return null;
    }

    @Override
    public int updateQuizStatistics(Map<Long, Boolean> quizData) {
        return 0;
    }
}
