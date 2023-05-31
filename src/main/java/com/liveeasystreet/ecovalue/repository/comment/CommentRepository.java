package com.liveeasystreet.ecovalue.repository.comment;

import com.liveeasystreet.ecovalue.domain.Comment;
import com.liveeasystreet.ecovalue.dto.comment.CommentGetDto;
import com.liveeasystreet.ecovalue.dto.comment.CommentUpdateDto;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    // 댓글 저장
    void insertComment(Comment comment);

    // 댓글 수정
    void edit(Long commentId, CommentUpdateDto commentUpdateDto);

    // 댓글 삭제
    void deleteById(Long commentId);

    // 해당 게시물의 전체 댓글 조회
    List<Comment> findByBoardId(Long boardId);

    // 해당 회원의 모든 댓글 조회
    List<Comment> findByMemberId(Long memberId);

    Optional<Comment> findById(Long commentId);
}
