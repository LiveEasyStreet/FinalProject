package com.liveeasystreet.ecovalue.cond.board;

import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.BoardCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Map;

@Data
@NoArgsConstructor
public class BoardSearchCond {

    // 제목
    private String title;

    // 내용
    private String contents;

    //카테고리
    private Long boardCategory;

    // 작성자
    private Long memberId;

    // 전체 검색
    public BoardSearchCond(BoardCategory boardCategory) {
        this.title = null;
        this.contents = null;
        this.boardCategory = boardCategory.getDataValue();
        this.memberId = null;
    }

    // 조건 검색
    public BoardSearchCond(String title, BoardCategory boardCategory, String contents, Long memberId) {
        this.title = title;
        if (boardCategory!=null){
            this.boardCategory = boardCategory.getDataValue();
        }
        else {
            this.boardCategory = null;
        }
        this.contents = contents;
        this.memberId = memberId;
    }

    public BoardSearchCond(Board board){
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.boardCategory = board.getBoardCategory();
        this.memberId = board.getMemberId();
    }
}
