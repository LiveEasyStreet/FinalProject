package com.liveeasystreet.ecovalue.dto.comment;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentGetDto {
    /**
     * 댓글을 db에서 가져올때 사용하는 Dto
     * 댓글은 memberId 를 통해 join 해서 member의 nickName을 가져와야됨
     */

    // 코멘트 번호
    private Long commentId;

    private String nickName;

    // 원댓글 id
    private Long headComment;

    // 작성 내용
    private String contents;

    // 업로드 날짜
    private LocalDateTime uploadDate;

    // 수정 날짜
    private LocalDateTime editDate;

    // 삭제 날짜
    private LocalDateTime deleteDate;

    public CommentGetDto(Long commentId,String nickName, Long headComment, String contents, LocalDateTime uploadDate, LocalDateTime editDate, LocalDateTime deleteDate) {
        this.commentId = commentId;
        this.nickName = nickName;
        this.headComment = headComment;
        this.contents = contents;
        this.uploadDate = uploadDate;
        this.editDate = editDate;
        this.deleteDate = deleteDate;
    }
}
