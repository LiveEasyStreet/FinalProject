package com.liveeasystreet.ecovalue.repository.comment;

import com.liveeasystreet.ecovalue.domain.Comment;
import com.liveeasystreet.ecovalue.dto.comment.CommentGetDto;
import com.liveeasystreet.ecovalue.dto.comment.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class MyBatisCommentRepository implements CommentRepository{

    private final CommentMapper commentMapper;
    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void edit(Long commentId, CommentUpdateDto commentUpdateDto) {
        commentMapper.edit(commentId,commentUpdateDto);
    }

    @Override
    public void deleteById(Long commentId) {
        commentMapper.deleteById(commentId);
    }

    @Override
    public List<Comment> findByBoardId(Long boardId) {
        return commentMapper.findByBoardId(boardId);
    }

    @Override
    public List<Comment> findByMemberId(Long memberId) {
        return commentMapper.findByMemberId(memberId);
    }
}
