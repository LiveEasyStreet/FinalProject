package com.liveeasystreet.ecovalue.dto.comment;

import com.liveeasystreet.ecovalue.domain.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String contents;

    // 업로드 날짜
    private String date;



    public CommentResponseDto(Long commentId, String nickName, String contents, String date) {
        this.commentId = commentId;
        this.nickName = nickName;
        this.contents = contents;
        this.date = date;
        this.headComment = null;
    }

    public CommentResponseDto(Long commentId, Long headComment, String nickName, String contents, String date) {
        this.commentId = commentId;
        this.headComment = headComment;
        this.nickName = nickName;
        this.contents = contents;
        this.date = date;
    }

    public CommentResponseDto(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.commentId = comment.getCommentId();
        this.headComment = comment.getHeadComment();
        this.nickName =  comment.getNickName();
        this.contents =  comment.getContents();
        if(comment.getEditDate() !=null){
            this.date = comment.getEditDate().format(formatter);
        }else{
            this.date = comment.getUploadDate().format(formatter);
        }
    }
}
