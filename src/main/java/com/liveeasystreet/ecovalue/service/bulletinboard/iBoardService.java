package com.liveeasystreet.ecovalue.service.bulletinboard;

import com.liveeasystreet.ecovalue.cond.board.BoardSearchCond;
import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.BoardCategory;
import com.liveeasystreet.ecovalue.domain.Comment;
import com.liveeasystreet.ecovalue.dto.board.BoardWriteDto;
import com.liveeasystreet.ecovalue.dto.comment.CommentGetDto;

import java.util.List;
import java.util.Optional;

public interface iBoardService {

    void save(BoardWriteDto board);

    public Board BoardViewService(Long boardId);

    List<Board> upGalleryBoardSearch(int pageSize, int offset, BoardCategory boardCategory, String search_target, String search_keyword);

    int upGalleryBoardSearchPageCount(int pageSize, BoardCategory boardCategory, String search_target, String search_keyword);
    List<Long> boardPageNum(List<Board> boardLists);

    List<Board> findAll(BoardSearchCond boardSearchCond);

    List<Comment> findByBoardId(Long boardId);

    void insertComment(Comment comment);

    Optional<Comment> findById(Long commentId);


}
