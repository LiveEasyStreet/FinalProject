package com.liveeasystreet.ecovalue.dto.board;

import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.BoardCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BoardViewDto {

    // 제목
    private String title;

    //카테고리
    private BoardCategory boardCategory;

    // 내용
    private String contents;

    // 닉네임
    private String nickName;

    // 최종 수정 날짜
    private LocalDate uploadDate;

    // 조회수
    private int views;


    public BoardViewDto(Board board) {
        this.title = board.getTitle();
        this.boardCategory = BoardCategory.findByDataValue(board.getBoardCategory());
        this.contents = board.getContents();
        if(board.getEditDate() != null){
            this.uploadDate=board.getEditDate();
        }
        else{
            this.uploadDate=board.getUploadDate();
        }
        this.views = board.getViews();

    }
}
