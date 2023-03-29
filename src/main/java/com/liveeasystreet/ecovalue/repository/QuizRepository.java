package com.liveeasystreet.ecovalue.repository;

import com.liveeasystreet.ecovalue.domain.Quiz;

import java.util.List;
import java.util.Optional;

/**
 * The interface Quiz repository.
 */
public interface QuizRepository {

    /**
     * 퀴즈 저장 메서드.
     * 매개변수로 퀴즈를 받아 저장한다.
     * @param quiz the quiz
     */
    void save(Quiz quiz);

    /**
     * 퀴즈 전체 검색(조회) 메서드.
     * 매개변수로 퀴즈 검색조건을 받아 현재 저장되어 있는 퀴즈 중 해당되는 모든 퀴즈 리스트를 반환.
     * @param cond the cond
     * @return the list
     */
    List<Quiz> findAll(QuizSearchCond cond);

    /**
     * 퀴즈의 key(id)목록을 전체 검색(조회)하는 메서드.
     * @return the list
     */
    List<Long> findAllkeyList();

    /**
     * 퀴즈 단건검색(조회) 메서드.
     * 매개변수로 id값을 받아 해당 id값을 가진 퀴즈를 검색(조회)한다.
     * @param id the id
     * @return the optional
     */
    Optional<Quiz> findById(Long id);

    /**
     * 퀴즈 수정 메서드(관리자용) ex. 제목, 내용 등...
     * 매개변수로 id값을 받아 해당되는 퀴즈 객체를 수정(변경)한다.
     * @param id          the id
     * @param updateParam the update param
     */
    void update(Long id, QuizDTO updateParam);

    /**
     * 퀴즈 수정 메서드.
     * 매개변수로 퀴즈를 받아 출제 횟수, 정답 횟수 등을 수정(변경)한다.
     * @param quiz the quiz
     */
    void update(Quiz quiz);

    /**
     * 퀴즈 삭제 메서드
     * 매개변수로 id값을 받아 해당되는 퀴즈를 삭제한다.
     * @param id the id
     */
    void delete(Long id);

}
