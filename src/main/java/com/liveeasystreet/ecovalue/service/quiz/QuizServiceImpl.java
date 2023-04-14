package com.liveeasystreet.ecovalue.service.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.QuizDto;
import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import com.liveeasystreet.ecovalue.repository.quiz.QuizSearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizServiceImpl implements IQuizService {

    private final QuizRepository quizRepository;


    /**
     * quiDto를 받아서 퀴즈를 생성하는 곳, 퀴즈 자체를 받아서 만들 수 있는 부분도 추가 예정
     * @param updateParam
     *
     */
    @Override
    public void resistQuiz(QuizDto updateParam) {
        Quiz quiz = new Quiz(updateParam.getCategory(), updateParam.getTitle(),
                updateParam.getDetail(), updateParam.getSolve(), updateParam.getAnswer());
        quizRepository.save(quiz);
    }


    /**
     * 퀴즈를 업데이트 하는 부분
     * 조건 : updateParam의 값 중 단 하나라도 null이 아니어야함
     * @param id
     * @param updateParam
     */
    @Override
    public void updateQuiz(Long id, QuizDto updateParam) {
        if ((updateParam.getCategory() !=null&& updateParam.getCategory()!="") ||
                (updateParam.getTitle() !=null&& updateParam.getTitle()!="") ||
                (updateParam.getDetail() !=null&& updateParam.getDetail()!="") ||
                (updateParam.getSolve() !=null&& updateParam.getSolve()!="") ||
                updateParam.getAnswer() !=null
        ){
            quizRepository.update(id, updateParam);
        }
    }

    /**
     * 퀴즈 삭제 부분
     * 퀴즈의 id를 받아서 퀴즈 삭제
     * @param idList
     */
    @Override
    public void deleteQuiz(Long... idList) {
        // 파라미터로 받은 id값을 가진 퀴즈를 찾아 삭제한다.
        for (Long id : idList) {
            quizRepository.delete(id);
        }
    }

    /**
     * 퀴즈 전체를 탐색
     * findAll cond값에 따라 달라짐
     * @return
     */
    @Override
    public List<Quiz> findAllQuiz(QuizSearchCond cond) {
        // 리포지토리에 저장되어 있는 모든 퀴즈를 반환한다.
        return quizRepository.findAll(cond);
    }

    @Override
    public Optional<Quiz> findQuiz(Long id) {
        //파라미터 값으로 받은 아이디로 레포지토리에서 퀴즈를 찾고 반환한다.
        //만약 퀴즈가 없다면 .orElseThrow()메소드로 예외를 던진다.
        return quizRepository.findById(id);
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
            if (!selectsQuiz.contains(allQuiz.get(randomNumber))) {
                selectsQuiz.add(allQuiz.get(randomNumber));
            }
        }

        return selectsQuiz;
    }


    @Override
    public int updateQuizStatistics(Map<Long, Boolean> quizData) {

        int score = 0;
        log.info("quizData : {}",quizData);

        //파라미터로 넘어온 퀴즈의 키와 값을 쓰기 위해 순회
        for (Map.Entry<Long, Boolean> getQuiz : quizData.entrySet()) {
            //파라미터로 넘어온 퀴즈의 ID로 특정 퀴즈를 찾는다.
            Quiz quiz = this.findQuiz(getQuiz.getKey()).orElse(null);

            if(quiz !=null){
                if(getQuiz.getValue()!=null){
                    quizRepository.update(quiz.getQuizId(),getQuiz.getValue());
                    if(getQuiz.getValue()){
                        score+=10;
                    }
                }
            }

        }

        return score;
    }
}
