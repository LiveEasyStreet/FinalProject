package com.liveeasystreet.ecovalue.controller.quiz;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.service.quiz.IQuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/quiz")
@Controller
@Slf4j
@RequiredArgsConstructor
public class QuizController {

    private final IQuizService quizService;

    /**
     * quiz 메인페이지 연결
     */
    @GetMapping
    public String quizMain() {
//        log.info("quizStarter invoked");
        return "quiz/quiz-main";
    }

    /**
     * 서비스의 selectsQuiz 메소드를 호출해 10개의 퀴즈를
     * 퀴즈 풀이 화면에 전달
     */
    @GetMapping("/question")
    public String question(Model model) {
//        log.info("question invoked");
        List<Quiz> quizList = quizService.selectsQuiz();
        model.addAttribute(quizList);
        return "quiz/quiz-question";
    }

    /**
     * 퀴즈 풀고 난 후 JS에서 전달한 Map 형식의 퀴즈 오답내역인 quizData를 받은 후
     * 서비스의 후처리 메소드를 호출 해 통계 정보를 업데이트 한 뒤
     * 점수 화면에 점수, 퀴즈 10개, 퀴즈 오답내역을 전달
     */
//    @GetMapping("/score")
//    public String score(@ModelAttribute QuizData quizData, Model model) {
////        log.info("score invoked");
//
//        if (quizData.getData() == null) {
//            throw new RuntimeException("quizData에 데이터가 없습니다.");
//        }
//
//        int score = quizService.updateQuizStatistics(quizData.getData());
//        Map<Long, Quiz> quizMap = new HashMap<>();
//
//        for (Long key : quizData.getData().keySet()) {
//            Quiz quiz = quizService.findQuiz(key);
//            quizMap.put(quiz.getId(), quiz);
//        }
//
//        model.addAttribute(score);
//        model.addAttribute(quizMap);
//        model.addAttribute(quizData);
//
//        return "quiz/score";
//    }
    @ResponseBody
    @PostMapping("/sendData")
    public QuizResultData score(@RequestBody Map<Long, Boolean> quizData) {
//        log.info("score invoked");
        log.info("quizData = {}", quizData);

        if (quizData == null) {
            throw new RuntimeException("quizData에 데이터가 없습니다.");
        }

        int score = quizService.updateQuizStatistics(quizData);
        Map<Long, Quiz> quizMap = new HashMap<>();

        for (Long key : quizData.keySet()) {
            Quiz quiz = quizService.findQuiz(key);
            quizMap.put(quiz.getId(), quiz);
        }

        return new QuizResultData(quizData, quizMap, score);
    }

    @GetMapping("score")
    public String result() {
        return "quiz/score";
    }
}
