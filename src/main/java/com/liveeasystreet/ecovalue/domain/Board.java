package com.liveeasystreet.ecovalue.domain;

import lombok.Data;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Board {

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
    private Long memberId;

    // 등록 날짜
    private LocalDateTime uploadDate;

    // 수정 날짜
    private LocalDateTime editDate;

    // 삭제 날짜
    private LocalDateTime deleteDate;

    // 조회수
    private int views;

    public Board(String title, BoardCategory boardCategory, String contents, Long memberId) {
        this.title = title;
        this.boardCategory = boardCategory.getDataValue();
        this.contents = contents;
        this.memberId = memberId;
        this.views=0;
    }

    public Board(String title, int tag, String contents) {
        this.title = title;
        this.tag = (long) tag;
        this.contents = contents;
    }

    public Board(Long boardId, String title, Long boardCategory, String contents, Long memberId, LocalDateTime uploadDate, LocalDateTime editDate, LocalDateTime deleteDate, int views) {
        this.boardId = boardId;
        this.title = title;
        this.boardCategory = boardCategory;
        this.contents = contents;
        this.memberId = memberId;
        this.uploadDate = uploadDate;
        this.editDate = editDate;
        this.deleteDate = deleteDate;
        this.views = views;
    }

    public Board(){
        this.boardId = null;
        this.title = null;
        this.boardCategory = null;
        this.contents = null;
        this.memberId = null;
        this.uploadDate = null;
        this.editDate = null;
        this.deleteDate = null;
        this.views = 0;
    }
}
