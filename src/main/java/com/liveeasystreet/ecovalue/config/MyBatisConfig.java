package com.liveeasystreet.ecovalue.config;

import com.liveeasystreet.ecovalue.repository.member.MemberMapper;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;
import com.liveeasystreet.ecovalue.repository.member.MyBatisMemberRepository;
import com.liveeasystreet.ecovalue.repository.quiz.MyBatisQuizRepository;
import com.liveeasystreet.ecovalue.repository.quiz.QuizMapper;
import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final MemberMapper memberMapper;
    private final QuizMapper quizMapper;

    @Bean
    public MemberRepository memberRepository(){
        return new MyBatisMemberRepository(memberMapper);
    }

    @Bean
    public QuizRepository quizRepository(){
        return new MyBatisQuizRepository(quizMapper);
    }

}
