package com.liveeasystreet.ecovalue.config;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.repository.quiz.JdbcQuizRepository;
import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class QuizConfig {

    private DataSource dataSource;

    public QuizConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public QuizRepository quizRepository(){

        return new JdbcQuizRepository(dataSource);
    }
}
