package com.liveeasystreet.ecovalue.domain;

import lombok.Data;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

@Data
public class Board {

    //내부 조회용 게시판 번호
    private Long boardId;

    // 제목
    private String title;

    //카테고리
    private Long boardCategory;

    // 내용
    private String contents;

    // 작성자
    private Long memberId;

    // 등록 날짜
    private LocalDate uploadDate;

    // 수정 날짜
    private LocalDate editDate;

    // 삭제 날짜
    private LocalDate deleteDate;

    // 조회수
    private int views;

    public Board(String title, BoardCategory boardCategory, String contents, Long memberId) {
        this.title = title;
        this.boardCategory = boardCategory.getDataValue();
        this.contents = contents;
        this.memberId = memberId;
        this.views=0;
    }

    public Board(Long boardId, String title, Long boardCategory, String contents, Long memberId, LocalDate uploadDate, LocalDate editDate, LocalDate deleteDate, int views) {
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
