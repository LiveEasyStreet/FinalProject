package com.liveeasystreet.ecovalue.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentResponseDto {

    // 코멘트 번호
    private Long commentId;

    // 상위 댓글
    private Long headComment;

    // 댓글 닉네임
    private String nickName;

    // 작성 내용
    private String content;

    // 업로드 날짜
    private String date;

    public CommentResponseDto(Long commentId, String nickName, String content, String date) {
        this.commentId = commentId;
        this.nickName = nickName;
        this.content = content;
        this.date = date;
        this.headComment = null;
    }
}
