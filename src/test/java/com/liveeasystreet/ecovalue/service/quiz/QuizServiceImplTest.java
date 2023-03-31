package com.liveeasystreet.ecovalue.service.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class QuizServiceImplTest {

    @Autowired
    IQuizService quizService;

    @Test
    void selectsQuiz() {
        List<Quiz> quizzes = quizService.selectsQuiz();

        Set<Long> quizIdSet = new HashSet<>();
        for (Quiz quiz : quizzes) {
            quizIdSet.add(quiz.getId());
        }

        Assertions.assertThat(quizIdSet.size()).isEqualTo(10);
    }
}