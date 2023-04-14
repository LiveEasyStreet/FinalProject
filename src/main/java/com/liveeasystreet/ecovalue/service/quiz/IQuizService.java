package com.liveeasystreet.ecovalue.service.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IQuizService {

    /**
     * <strong>퀴즈 생성 메서드</strong><br><hr>
     * 퀴즈 객체를 생성하고 파라미터 정보로 넘어온 값들을 불러와
     * 퀴즈 객체에 저장<br> Ex> Quiz.setXxx(updateParam.getXxx);<br>
     * 후에 리포지토리에 저장
     * @param updateParam
     */
    void resistQuiz(QuizDto updateParam);

    /**
     * <strong>퀴즈 수정 메서드</strong><br><hr>
     * 퀴즈 ID 값을 파라미터로 받아 특정 퀴즈를 찾고 변경값을 퀴즈 객체에 저장 후
     * <br>리포지토리에 저장
     * @param id
     * @param updateParam
     */
    void updateQuiz(Long id, QuizDto updateParam);

    /**
     * <strong>퀴즈 삭제 메서드</strong><br><hr>
     * 퀴즈 아이디를 파라미터 값으로 받아 특정 퀴즈를 삭제하는 메서드<br>
     * 필요에 따라 파라미터 값으로 여러개의 아이디를 받고 반복 삭제 작업 수행
     * @param idList
     */
    void deleteQuiz(Long...idList);

    /**
     * <strong>퀴즈 전체조회 메서드</strong><br><hr>
     * 리포지토리에서 퀴즈를 전체조회하는 메서드
     * @return List
     */
    List<Quiz> findAllQuiz();

    /**
     * <strong>퀴즈 선택조회 메서드</strong><br><hr>
     * 파라미터 값으로 아이디를 받아 리포지토리에서 조회 하는 메서드
     * @param id
     * @return Optional
     */
    Optional<Quiz> findQuiz(Long id);

    /**
     * <strong>문제 출제 메서드</strong><br><hr>
     * 리포지토리에서 퀴즈들을 꺼내와 섞은 후에 랜덤으로 10문제를 추출해
     * 클라이언트에게 제공한다.
     * @return List
     */
    List<Quiz> selectsQuiz();

    /**
     * <strong>퀴즈 통계 메서드</strong><br><hr>
     * 퀴즈를 푼 결과 값을 Map<퀴즈 아이디, 정답여부> quizData 으로 받는다.<br>
     * 그 후 퀴즈 필드의 맞춘 횟수, 나온 횟수를 카운트 해주는 메서드
     * @param quizData
     * @return int
     */
    int updateQuizStatistics(Map<Long, Boolean> quizData);
}
