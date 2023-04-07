package com.liveeasystreet.ecovalue;

import com.liveeasystreet.ecovalue.config.MyBatisConfig;
import com.liveeasystreet.ecovalue.repository.quiz.JdbcQuizRepository;
import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Slf4j
@SpringBootApplication
public class EcovalueApplication {


	public static void main(String[] args) {
		SpringApplication.run(EcovalueApplication.class, args);
	}

	@Bean
	@Profile({"local", "test"})
	public TestQuizDataInit testQuizDataInit(QuizRepository quizRepository) {
		if (quizRepository instanceof JdbcQuizRepository) {
			return null;
		}
		return new TestQuizDataInit(quizRepository);
	}

}
