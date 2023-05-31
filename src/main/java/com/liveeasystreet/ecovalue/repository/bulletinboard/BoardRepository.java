package com.liveeasystreet.ecovalue.repository.bulletinboard;

import com.liveeasystreet.ecovalue.cond.board.BoardSearchCond;
import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.dto.board.BoardUpdateDto;
import com.liveeasystreet.ecovalue.dto.board.BoardWriteDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    // 게시물 작성(저장)
    void save(BoardWriteDto board);

    // 게시물 삭제
    void deleteById(Long boardId);

    // 게시물 업데이트
    void update(Long boardId, BoardUpdateDto boardUpdateDto);

    // 게시물 전체 조회
    List<Board> findAll(BoardSearchCond boardSearchCond);

    // 게시물 전체 조회 with Paging
    List<Board> findAllWithPaging(int pageSize, int offset,BoardSearchCond boardSearchCond);

    // 검색 결과에 따른 총 페이지 수
    int pageCount(int pageSize, BoardSearchCond boardSearchCond);


    // 작성자 id로 조회
    Optional<Board> findById(Long boardId);

    Long findLength();

    Long viewCountUp(Long boardId);
}
