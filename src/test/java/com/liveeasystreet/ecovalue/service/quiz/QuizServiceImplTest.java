package com.liveeasystreet.ecovalue.service.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDto;
import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class QuizServiceImplTest {

    @Autowired
    IQuizService quizService;

    @Autowired
    QuizRepository quizRepository;

    @Test
    void selectsQuiz() {
        List<Quiz> quizzes = quizService.selectsQuiz();

        Set<Long> quizIdSet = new HashSet<>();
        for (Quiz quiz : quizzes) {
            quizIdSet.add(quiz.getQuizId());
        }

        Assertions.assertThat(quizIdSet.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("업데이트 서비스 부분 확인")
    void update(){
        Quiz quiz1 = new Quiz("캔","캔 분리수거에 대한 OX 퀴즈","알루미늄캔은 재활용이 가능한 것일까요?","알루미늄캔은 재활용이 가능합니다. 알루미늄은 자원의 낭비를 줄이고 환경 보호를 위해 재활용이 권장되는 재료 중 하나입니다. 따라서 알루미늄캔은 재활용이 가능하며, 분리수거 시 알루미늄캔만 따로 모아 처리해야 합니다.",true);

        quizRepository.save(quiz1);

        assertThat(quizRepository.findAllKeyList().size()).isEqualTo(1);
        Long id = quizRepository.findAllKeyList().get(0);

        //dto가 null일때 점검 #1
        QuizDto quizDto = new QuizDto();
        quizService.updateQuiz(id, quizDto);
        Quiz quiz = quizRepository.findById(id).orElse(null);
//        log.info("quiz : {}",quiz);
//        log.info("quiz1 : {}",quiz1);
        assertThat(quiz).isEqualTo(quiz1);

        //dto가 null일때 점검 #2
        quizDto.setCategory("");
        quizDto.setTitle("");
        quizDto.setSolve("");
        quizDto.setDetail("");
        quizDto.setAnswer(null);
        quizService.updateQuiz(id, quizDto);
        quiz = quizRepository.findById(id).orElse(null);
        assertThat(quiz).isEqualTo(quiz1);
    }


    @Test
    @DisplayName("퀴즈 기록 업데이트")
    void updateQuizStatistics(){
        Quiz quiz1 = new Quiz("캔","캔 분리수거에 대한 OX 퀴즈","알루미늄캔은 재활용이 가능한 것일까요?","알루미늄캔은 재활용이 가능합니다. 알루미늄은 자원의 낭비를 줄이고 환경 보호를 위해 재활용이 권장되는 재료 중 하나입니다. 따라서 알루미늄캔은 재활용이 가능하며, 분리수거 시 알루미늄캔만 따로 모아 처리해야 합니다.",true);
        Quiz quiz2 = new Quiz("캔","캔 분리수거에 대한 OX 퀴즈","알루미늄캔은 재활용이 가능한 것일까요?","알루미늄캔은 재활용이 가능합니다. 알루미늄은 자원의 낭비를 줄이고 환경 보호를 위해 재활용이 권장되는 재료 중 하나입니다. 따라서 알루미늄캔은 재활용이 가능하며, 분리수거 시 알루미늄캔만 따로 모아 처리해야 합니다.",true);
        quizRepository.save(quiz1);
        quizRepository.save(quiz2);
        Map<Long,Boolean> testSet = new HashMap<>();
        testSet.put(quiz2.getQuizId(), true);
        testSet.put(quiz1.getQuizId(),false);
        quizService.updateQuizStatistics(testSet);

        log.info("결과 : {}",quizRepository.findAll(null));

    }
}