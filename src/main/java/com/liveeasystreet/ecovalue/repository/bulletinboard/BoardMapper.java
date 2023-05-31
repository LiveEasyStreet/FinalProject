package com.liveeasystreet.ecovalue.repository.bulletinboard;

import com.liveeasystreet.ecovalue.cond.board.BoardSearchCond;
import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.dto.board.BoardUpdateDto;
import com.liveeasystreet.ecovalue.dto.board.BoardWriteDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    // 게시물 작성(저장)
    void save(BoardWriteDto board);

    // 게시물 삭제
    void deleteById(Long boardId);

    // 게시물 업데이트
    void update(Long boardId, BoardUpdateDto boardUpdateDto);

    // 게시물 전체 조회
    List<Board> findAll(BoardSearchCond boardSearchCond);

    // 페이징 있는 findAll
    List<Board> findAllWithPaging(int pageSize,int offset,BoardSearchCond boardSearchCond);

    // 검색 결과에 따른 총 페이지 수
    int pageCount(int pageSize, BoardSearchCond boardSearchCond);
    // 작성자 id로 조회
    Optional<Board> findById(Long boardId);

    //전체길이 조회일뿐임
    Long findLength();

    //조회수 증가
    Long viewCountUp(Long boardId);
}
