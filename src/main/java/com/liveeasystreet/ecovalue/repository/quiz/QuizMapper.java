package com.liveeasystreet.ecovalue.repository.quiz;


import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface QuizMapper {

    void save(Quiz quiz);

    List<Quiz> findAll(QuizSearchCond cond);

    List<Long> findAllKeyList();

    Optional<Quiz> findById(Long id);

    void update(@Param("quizId") Long id,@Param("updateParam") QuizDto updateParam);

    void updateScore(@Param("quizId") Long id, @Param("score")Boolean score);

    void delete(Long id);

}
