package com.liveeasystreet.ecovalue;

import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class EcovalueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcovalueApplication.class, args);
	}

	@Bean
	@Profile({"local", "test"})
	public TestQuizDataInit testQuizDataInit(QuizRepository quizRepository) {
		return new TestQuizDataInit(quizRepository);
	}
}
