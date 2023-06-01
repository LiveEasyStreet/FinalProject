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
    private String  nickName;

    // 전체 검색
    public BoardSearchCond(BoardCategory boardCategory) {
        this.title = null;
        this.contents = null;
        this.boardCategory = boardCategory.getDataValue();
        this.nickName = null;
    }



    // 조건 검색
    public BoardSearchCond(String title, BoardCategory boardCategory, String contents, String nickName) {
        this.title = title;
        if (boardCategory!=null){
            this.boardCategory = boardCategory.getDataValue();
        }
        else {
            this.boardCategory = null;
        }
        this.contents = contents;
        this.nickName = nickName;
    }


}
