package com.liveeasystreet.ecovalue.quiz.service;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDTO;
import com.liveeasystreet.ecovalue.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements IQuizService {

    private final QuizRepository quizRepository;

    @Override
    public void resistQuiz(QuizDTO updateParam) {

    }

    @Override
    public void updateQuiz(Long id, QuizDTO updateParam) {

    }

    @Override
    public void deleteQuiz(Long...idList) {
        // 파라미터로 받은 id값을 가진 퀴즈를 찾아 삭제한다.
        for(Long id:idList) {
            quizRepository.delete(id);
        }
    }

    @Override
    public List<Quiz> findAllQuiz() {
        // 리포지토리에 저장되어 있는 모든 퀴즈를 반환한다.
        return quizRepository.findAll();
    }

    @Override
    public Quiz findQuiz(Long id) {
        return null;
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
