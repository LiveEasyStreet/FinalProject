package com.liveeasystreet.ecovalue.repository.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDto;
import com.liveeasystreet.ecovalue.service.quiz.QuizServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
public class quizTest {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizServiceImpl quizService;

    @Test
    @DisplayName("quiz 저장")
    public void save(){
        Quiz quiz = new Quiz("캔","캔 분리수거에 대한 OX 퀴즈","알루미늄캔은 재활용이 가능한 것일까요?","알루미늄캔은 재활용이 가능합니다. 알루미늄은 자원의 낭비를 줄이고 환경 보호를 위해 재활용이 권장되는 재료 중 하나입니다. 따라서 알루미늄캔은 재활용이 가능하며, 분리수거 시 알루미늄캔만 따로 모아 처리해야 합니다.",true);

        log.info("quiz 내용 :{}",quiz);
        quizRepository.save(quiz);

        Quiz getItem = quizRepository.findById(quiz.getQuizId()).get();
        assertThat(quiz).isEqualTo(getItem);
    }

    @Test
    @DisplayName("quiz 탐색")
    public void findAll(){
        Quiz quiz1 = new Quiz("캔","캔 분리수거에 대한 OX 퀴즈","알루미늄캔은 재활용이 가능한 것일까요?","알루미늄캔은 재활용이 가능합니다. 알루미늄은 자원의 낭비를 줄이고 환경 보호를 위해 재활용이 권장되는 재료 중 하나입니다. 따라서 알루미늄캔은 재활용이 가능하며, 분리수거 시 알루미늄캔만 따로 모아 처리해야 합니다.",true);
        Quiz quiz2 = new Quiz("플라스틱","플라스틱 분리수거에 대한 OX 퀴즈","투명한 플라스틱병만 재활용이 가능한 것일까요?","플라스틱병은 종류에 따라 재활용이 가능하거나 불가능한 경우가 있습니다. 일반적으로 PET 플라스틱병은 재활용이 가능하지만, PVC, PP, PS 등의 플라스틱병은 재활용이 어렵거나 불가능합니다.",false);
        Quiz quiz3 = new Quiz("플라스틱","플라스틱 분리수거에 대한 OX 퀴즈","플라스틱 물병은 분리수거가 가능한 것일까요?","플라스틱 물병은 재활용이 가능한 분리수거 대상입니다. 다만, 재활용을 위해서는 플라스틱 뚜껑과 라벨을 따로 분리해서 처리해야 하며, 물병 내부에 내용물이 남아 있으면 세척해주어야 합니다.",true);

        quizRepository.save(quiz1);
        quizRepository.save(quiz2);
        quizRepository.save(quiz3);

//        log.info("quiz1 : {}",quiz1);
//        log.info("quiz1 : {}",quiz2);
//        log.info("quiz1 : {}",quiz3);

        //전체 검색 확인
        test(null,null,null,quiz1,quiz2,quiz3);

        //카테고리 검색 확인
        test("캔",null,null,quiz1);
        test("플라스틱",null,null,quiz2,quiz3);

        //제목 일부분 검색 확인
        test(null,"플라스틱",null,quiz2,quiz3);

        //퀴즈 내용 검색 확인
        test(null,null,"재활용",quiz1,quiz2);

        //잘못된 검색으로 검색 결과 없음 확인
        test("자연",null,null);
    }

    @Test
    @DisplayName("업데이트 확인")
    public void update(){
        Quiz quiz1 = new Quiz("캔","캔 분리수거에 대한 OX 퀴즈","알루미늄캔은 재활용이 가능한 것일까요?","알루미늄캔은 재활용이 가능합니다. 알루미늄은 자원의 낭비를 줄이고 환경 보호를 위해 재활용이 권장되는 재료 중 하나입니다. 따라서 알루미늄캔은 재활용이 가능하며, 분리수거 시 알루미늄캔만 따로 모아 처리해야 합니다.",true);

        quizRepository.save(quiz1);

        assertThat(quizRepository.findAllKeyList().size()).isEqualTo(1);
        Long id = quizRepository.findAllKeyList().get(0);


        QuizDto quizDto = new QuizDto();

        //하나라도 null이 아닐때 점검
        quizDto.setCategory("플라스틱");
        quizService.updateQuiz(id,quizDto);
        Quiz quiz = quizRepository.findById(id).orElse(null);
        assertThat(quiz.getCategory()).isEqualTo("플라스틱");
    }
    @Test
    @DisplayName("출현빈도 테스트")
    public void countIncrease(){
        Quiz quiz1 = new Quiz("캔","캔 분리수거에 대한 OX 퀴즈","알루미늄캔은 재활용이 가능한 것일까요?","알루미늄캔은 재활용이 가능합니다. 알루미늄은 자원의 낭비를 줄이고 환경 보호를 위해 재활용이 권장되는 재료 중 하나입니다. 따라서 알루미늄캔은 재활용이 가능하며, 분리수거 시 알루미늄캔만 따로 모아 처리해야 합니다.",true);

        quizRepository.save(quiz1);

        quizRepository.update(quiz1.getQuizId(),true);
        log.info("카운트 증가 : {}", quizRepository.findById(quiz1.getQuizId()));
    }

    @Test
    @DisplayName("Key값 겁색 확인 ")
    public void findKeyAndDelete(){
        Quiz quiz1 = new Quiz("캔","캔 분리수거에 대한 OX 퀴즈","알루미늄캔은 재활용이 가능한 것일까요?","알루미늄캔은 재활용이 가능합니다. 알루미늄은 자원의 낭비를 줄이고 환경 보호를 위해 재활용이 권장되는 재료 중 하나입니다. 따라서 알루미늄캔은 재활용이 가능하며, 분리수거 시 알루미늄캔만 따로 모아 처리해야 합니다.",true);
        Quiz quiz2 = new Quiz("플라스틱","플라스틱 분리수거에 대한 OX 퀴즈","투명한 플라스틱병만 재활용이 가능한 것일까요?","플라스틱병은 종류에 따라 재활용이 가능하거나 불가능한 경우가 있습니다. 일반적으로 PET 플라스틱병은 재활용이 가능하지만, PVC, PP, PS 등의 플라스틱병은 재활용이 어렵거나 불가능합니다.",false);
        Quiz quiz3 = new Quiz("플라스틱","플라스틱 분리수거에 대한 OX 퀴즈","플라스틱 물병은 분리수거가 가능한 것일까요?","플라스틱 물병은 재활용이 가능한 분리수거 대상입니다. 다만, 재활용을 위해서는 플라스틱 뚜껑과 라벨을 따로 분리해서 처리해야 하며, 물병 내부에 내용물이 남아 있으면 세척해주어야 합니다.",true);

        quizRepository.save(quiz1);
        quizRepository.save(quiz2);
        quizRepository.save(quiz3);

        List<Long> resultKeySet = quizRepository.findAllKeyList();
        assertThat(resultKeySet).contains(1L);
        assertThat(resultKeySet).contains(2L);
        assertThat(resultKeySet).contains(3L);

        //삭제된 후 검색되는지 확인
        quizRepository.delete(3L);

        Quiz quiz = quizRepository.findById(3L).orElse(null);

        assertThat(quiz).isNull();

    }

    //검색 테스트
    void test(String category, String title,String detail, Quiz... quizzes) {
        List<Quiz> result = quizRepository.findAll(new QuizSearchCond(category,title,detail));
        assertThat(result).containsExactly(quizzes);
    }
}
