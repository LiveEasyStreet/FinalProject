package com.liveeasystreet.ecovalue.repository.board;

import com.liveeasystreet.ecovalue.cond.board.BoardSearchCond;
import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.BoardCategory;
import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.repository.bulletinboard.BoardRepository;
import com.liveeasystreet.ecovalue.repository.quiz.QuizSearchCond;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
public class boardTest {

    @Autowired
    BoardRepository boardRepository;


    @Test
    @DisplayName("저장 테스트")
    public void save(){
        Board board1 = new Board("테스트 제목 1", BoardCategory.UP_CYCLE,"테스트 데이터1",1L);
        Board board2 = new Board("테스트 제목 2", BoardCategory.UP_CYCLE,"테스트 데이터2",2L);
        boardRepository.save(board1);
        boardRepository.save(board2);
        log.info("{}",board1);

        test("테스트 제목 1",null,null,1L,board1);

    }

    @Test
    @DisplayName("findAll 테스트")
    public void findAll(){


    }

    //검색 테스트
    void test(String title, BoardCategory category,String contents, Long memberId, Board... boards) {
        log.info("title : {}, category :  {}, contents : {}, memberId : {}",title,category,contents,memberId);
        log.info("{}", boardRepository.findLength());

        List<Board> result = boardRepository.findAll(new BoardSearchCond(title,category,contents,memberId));
        log.info("result : {}",result);
        assertThat(result).containsExactly(boards);
    }
}
