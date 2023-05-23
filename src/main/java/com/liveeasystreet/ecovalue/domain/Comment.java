package com.liveeasystreet.ecovalue.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {

    // 코멘트 번호
    private Long commentId;

    //게시판 번호
    private Long boardId;

    //회원번호
    private Long memberId;

    // 원댓글 id
    private Long headComment;

    // 작성 내용
    private String content;

    // 업로드 날짜
    private LocalDateTime uploadDate;

    // 수정 날짜
    private LocalDateTime editDate;

    // 삭제 날짜
    private LocalDateTime deleteDate;

}
