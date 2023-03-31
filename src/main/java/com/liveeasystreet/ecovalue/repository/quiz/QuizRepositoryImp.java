package com.liveeasystreet.ecovalue.repository.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDTO;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QuizRepositoryImp implements QuizRepository {

    private static Map<Long, Quiz> store = new HashMap<>();
    private static Long Sequence = 0L;

    @Override
    public void save(Quiz quiz) {
        Sequence++;
        quiz.setId(Sequence);
        store.put(quiz.getId(), quiz);
    }

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
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void update(Long id, QuizDTO updateParam) {
        if (store.containsKey(id)) {
            Quiz quiz = store.get(id);
            quiz.setTitle(updateParam.getTitle());
            quiz.setDetail(updateParam.getDetail());
            quiz.setSolve(updateParam.getSolve());
            quiz.setCategory(updateParam.getCategory());
            quiz.setAnswer(updateParam.getAnswer());
        }
    }

    @Override
    public void update(Quiz quiz) {
        Long id = quiz.getId();
        Quiz savedQuiz = this.findById(id).orElseThrow(() -> new IllegalArgumentException("store 안에 해당 퀴즈가 없습니다."));
        savedQuiz.setOccurredProblemCount(quiz.getOccurredProblemCount());
        savedQuiz.setNumberOfHits(quiz.getNumberOfHits());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}
