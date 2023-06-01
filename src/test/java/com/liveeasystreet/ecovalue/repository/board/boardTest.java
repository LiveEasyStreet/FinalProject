package com.liveeasystreet.ecovalue.repository.board;

import com.liveeasystreet.ecovalue.cond.board.BoardSearchCond;
import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.BoardCategory;
import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.domain.Quiz;
import com.liveeasystreet.ecovalue.dto.board.ThumbsUpDto;
import com.liveeasystreet.ecovalue.repository.bulletinboard.BoardRepository;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;
import com.liveeasystreet.ecovalue.repository.quiz.QuizSearchCond;
import com.liveeasystreet.ecovalue.repository.thumbsup.ThumbUpRepository;
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

    @Autowired
    ThumbUpRepository thumbUpRepository;

    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("저장 테스트")
    public void save(){
        Member member = new Member();
        member.setLoginId("test11");
        member.setMemberPassword("test11");
        member.setNickName("테스트1");
        member.setMemberName("테스트");
        member.setEmail("test@naver.com");

        // member 객체 db 에 저장
        memberRepository.save(member);
        member.setLoginId("test12");
        member.setMemberPassword("test12");
        member.setNickName("테스트2");
        member.setEmail("test1@naver.com");
        memberRepository.save(member);

        Board board1 = new Board("테스트 제목 1", BoardCategory.UP_CYCLE,"테스트 데이터1",1L);
        Board board2 = new Board("테스트 제목 2", BoardCategory.UP_CYCLE,"테스트 데이터2",2L);
        boardRepository.save(board1);
        boardRepository.save(board2);
        log.info("{}",board1);

        test("테스트 제목 1",null,null,"1",board1);
        test("테스트 제목",null,null,"1",board1);
        test("테스트 제목",null,null,null,board1,board2);
        test(null,null,null,null,board1,board2);
        test(null,null,null,"테스트",board1,board2);
        test(null,null,null,"테스트2",board2);

    }

    @Test
    @DisplayName("view Count up 테스트")
    public void findAll(){
        Board board1 = new Board("테스트 제목 1", BoardCategory.UP_CYCLE,"테스트 데이터1",1L);
        boardRepository.save(board1);
        log.info("board update return : {}",boardRepository.viewCountUp(1L));
        log.info("board update return : {}",boardRepository.viewCountUp(3L));

    }

    @Test
    @DisplayName("좋아요 기능 테스트")
    public void findAllThumb(){
        Board board1 = new Board("테스트 제목 1", BoardCategory.UP_CYCLE,"테스트 데이터1",1L);
        boardRepository.save(board1);
        Member member = new Member();
        member.setLoginId("test11");
        member.setMemberPassword("test11");
        member.setNickName("테스트");
        member.setMemberName("테스트");
        member.setEmail("test@naver.com");

        // member 객체 db 에 저장
        memberRepository.save(member);
        ThumbsUpDto thumbsUpDto = new ThumbsUpDto(1L,1L);
        thumbUpRepository.thumbsUp(thumbsUpDto);
        log.info("thumbUp : {}",thumbUpRepository.isThumbUp(thumbsUpDto));

    }
    @Test
    @DisplayName("추천 기능 숫자 확인")
    public void findThumb(){
        Board board1 = new Board("테스트 제목 1", BoardCategory.UP_CYCLE,"테스트 데이터1",1L);
        boardRepository.save(board1);
        Member member = new Member();
        member.setLoginId("test11");
        member.setMemberPassword("test11");
        member.setNickName("테스트");
        member.setMemberName("테스트");
        member.setEmail("test@naver.com");

        // member 객체 db 에 저장
        memberRepository.save(member);
        member.setLoginId("test12");
        member.setMemberPassword("test12");
        member.setNickName("테스트1");
        member.setEmail("test1@naver.com");
        memberRepository.save(member);

        assertThat(thumbUpRepository.thumbCount(1L)).isEqualTo(0);

        ThumbsUpDto thumbsUpDto = new ThumbsUpDto(1L,1L);
        thumbUpRepository.thumbsUp(thumbsUpDto);
        log.info("thumb up count : {}",thumbUpRepository.thumbCount(1L));
        assertThat(thumbUpRepository.thumbCount(1L)).isEqualTo(1);


        thumbUpRepository.thumbsUp(new ThumbsUpDto(1L,2L));
        log.info("thumb up count : {}",thumbUpRepository.thumbCount(1L));
        assertThat(thumbUpRepository.thumbCount(1L)).isEqualTo(2);
    }

    //검색 테스트
    void test(String title, BoardCategory category,String contents, String nickName, Board... boards) {
        log.info("title : {}, category :  {}, contents : {}, memberId : {}",title,category,contents,nickName);
        log.info("{}", boardRepository.findLength());

        // boardSearchCond 의 변경으로 인한 변경 필요 memberId 대신 loginId로 검색
        List<Board> result = boardRepository.findAll(new BoardSearchCond(title,category,contents,nickName));
        log.info("result : {}",result);
        assertThat(result).containsExactly(boards);
    }
}
