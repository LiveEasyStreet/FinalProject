package com.liveeasystreet.ecovalue.repository.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDTO;

import java.util.*;

public class QuizRepositoryImpl implements QuizRepository {

    private Map<Long, Quiz> store = new HashMap<>();

    @Override
    public void save(Quiz quiz) {
        store.put(quiz.getId(), quiz);
        
    } //save

    @Override
    public List<Quiz> findAll(QuizSearchCond cond) {

        return new ArrayList<>(store.values());
    }

    @Override
    public List<Long> findAllkeyList() {

        return new ArrayList<>(store.keySet());
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        Quiz quiz = store.get(id);


        return Optional.ofNullable(quiz);
    }

    @Override
    public void update(Long id, QuizDTO updateParam) {

    }

    @Override
    public void update(Quiz quiz) {

    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
