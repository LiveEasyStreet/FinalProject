package com.liveeasystreet.ecovalue.service.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDTO;
import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements IQuizService {

    private final QuizRepository quizRepository;

    @Override
    public void resistQuiz(QuizDTO updateParam) {

    }

    @Override
    public void updateQuiz(Long id, QuizDTO updateParam) {

    }

    @Override
    public void deleteQuiz(Long...idList) {
        // 파라미터로 받은 id값을 가진 퀴즈를 찾아 삭제한다.
        for(Long id:idList) {
            quizRepository.delete(id);
        }
    }

    @Override
    public List<Quiz> findAllQuiz() {
        // 리포지토리에 저장되어 있는 모든 퀴즈를 반환한다.
        return quizRepository.findAll(null);
    }

    @Override
    public Quiz findQuiz(Long id) {
        //파라미터 값으로 받은 아이디로 레포지토리에서 퀴즈를 찾고 반환한다.
        //만약 퀴즈가 없다면 .orElseThrow()메소드로 예외를 던진다.
        return quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리포지토리에 해당 퀴즈가 없습니다."));
    }

    @Override
    public List<Quiz> selectsQuiz() {
        List<Quiz> allQuiz = quizRepository.findAll(null); //퀴즈 전체 목록
        List<Quiz> selectsQuiz = new ArrayList<>(); //반환될 퀴즈 목록

        //퀴즈 전체 목록에 10개의 퀴즈가 없다면 예외를 던진다.
        if (allQuiz.size() < 10) {
            throw new IllegalArgumentException("퀴즈의 총 숫자가 10개 보다 적습니다.");
        }

        //반복할 때 마다 생성되는 난수 값으로 특정 퀴즈를 반환될 퀴즈 목록에 담는다.
        while (selectsQuiz.size() < 10) {
            int randomNumber = new Random().nextInt(allQuiz.size());
            selectsQuiz.add(allQuiz.get(randomNumber));
        }

        return selectsQuiz;
    }

    @Override
    public void updateQuizStatistics(Map<Long, Boolean> quizData) {
        //파라미터로 넘어온 퀴즈의 키와 값을 쓰기 위해 순회
        for (Map.Entry<Long, Boolean> getQuiz : quizData.entrySet()) {
            //파라미터로 넘어온 퀴즈의 ID로 특정 퀴즈를 찾는다.
            Quiz quiz = this.findQuiz(getQuiz.getKey());

            //특정 퀴즈의 맞춘 횟수를 카운트
            quiz.setOccurredProblemCount(quiz.getOccurredProblemCount() + 1);

            //특정 퀴즈의 맞춘 횟수를 카운트 하기 위해 조건문으로 확인 후 카운트
            if (getQuiz.getValue() == true) {
                quiz.setNumberOfHits(quiz.getNumberOfHits() + 1);
            }
        }
    }
}
