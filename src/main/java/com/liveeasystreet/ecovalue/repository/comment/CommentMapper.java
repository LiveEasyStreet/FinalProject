package com.liveeasystreet.ecovalue.repository.comment;

import com.liveeasystreet.ecovalue.domain.Comment;
import com.liveeasystreet.ecovalue.dto.comment.CommentGetDto;
import com.liveeasystreet.ecovalue.dto.comment.CommentUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {

    // 댓글 저장
    void insertComment(Comment comment);

    Optional<Comment> findById(Long commentId);

    // 댓글 수정
    void edit(Long commentId, CommentUpdateDto commentUpdateDto);

    // 댓글 삭제
    void deleteById(Long commentId);

    // 해당 게시물의 전체 댓글 조회
    List<Comment> findByBoardId(Long boardId);

    // 해당 회원의 모든 댓글 조회
    List<Comment> findByMemberId(Long memberId);
}
