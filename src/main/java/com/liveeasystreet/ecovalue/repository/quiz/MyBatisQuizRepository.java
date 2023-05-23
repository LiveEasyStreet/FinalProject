package com.liveeasystreet.ecovalue.repository.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Primary
@Repository
@RequiredArgsConstructor
public class MyBatisQuizRepository implements QuizRepository{

    private final QuizMapper quizMapper;

    @Override
    public void save(Quiz quiz) {
        quizMapper.save(quiz);
    }

    @Override
    public List<Quiz> findAll(QuizSearchCond cond) {
        return quizMapper.findAll(cond);
    }

    @Override
    public List<Long> findAllKeyList() {
        return quizMapper.findAllKeyList();
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return quizMapper.findById(id);
    }

    @Override
    public void update(Long id, QuizDto updateParam) {
        quizMapper.update(id,updateParam);
    }


    @Override
    public void update(Long id, Boolean score) {
        quizMapper.updateScore(id,score);
    }

    @Override
    public void delete(Long id) {
        quizMapper.delete(id);
    }
}
