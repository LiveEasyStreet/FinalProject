package com.liveeasystreet.ecovalue;

import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.repository.quiz.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TestQuizDataInit {

    private final QuizRepository quizRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() throws IOException {
        
        log.info("Quiz 데이터를 초기화 합니다.");
        
        // repository에 있는 모든 퀴즈 제거
        List<Quiz> allQuizList = quizRepository.findAll(null);
        for (Quiz quiz : allQuizList) {
            quizRepository.delete(quiz.getId());
        }

        // 퀴즈 파일 경로 지정
        File file = new File("src/main/resources/data/recycleQuiz.xlsx");

        // fileInputStream으로 해당 파일 읽음
        try (FileInputStream inputStream = new FileInputStream(file)) {

            // Workbook 타입으로 변환
            Workbook workbook = new XSSFWorkbook(inputStream);
            // 거기서 하나의 sheet를 고름(처음 sheet)
            Sheet sheet = workbook.getSheetAt(0);

            // 퀴즈 갯수인 행의 갯수 만큼 순회
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {

                // 하나의 행 선택
                Row row = sheet.getRow(i);

                // 행의 col을 값을 통해 Quiz 도메인 생성
                Quiz quiz = Quiz.createQuiz(row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),
                        row.getCell(3).getStringCellValue(),
                        row.getCell(4).getBooleanCellValue());

//                log.info("quiz = {}", quiz);

                // 리포지토리에 저장
                quizRepository.save(quiz);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
