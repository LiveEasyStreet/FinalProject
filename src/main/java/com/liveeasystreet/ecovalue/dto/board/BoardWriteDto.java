package com.liveeasystreet.ecovalue.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardWriteDto {

    //내부 조회용 게시판 번호
    private Long boardId;

    // 제목
    private String title;

    //카테고리
    private Long boardCategory;

    /**
     * 태그인데 아직 db 구현 덜함
     */
    private Long tag;

    // 내용
    private String contents;

    // 작성자
    private String loginId;

    // 등록 날짜
    private LocalDateTime uploadDate;

    // 조회수
    private int views;

    public BoardWriteDto() {
    }

    public BoardWriteDto(String title, Long boardCategory, Long tag, String contents, String memberId, LocalDateTime uploadDate) {
        this.title = title;
        this.boardCategory = boardCategory;
        this.tag = tag;
        this.contents = contents;
        this.loginId = memberId;
        this.uploadDate = uploadDate;
        this.views = 0;
    }

    public BoardWriteDto(String title, Long tag, String contents) {
        this.title = title;
        this.tag = tag;
        this.contents = contents;
        this.views = 0;
    }
}
