package com.liveeasystreet.ecovalue.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ThumbsUpResponseDto {

    public boolean processCompleted;

    public boolean thumbAct;

    public int boardUpCount;

    public ThumbsUpResponseDto() {
        this.processCompleted =false;
        this.thumbAct =false;
        boardUpCount=0;

    }

    public ThumbsUpResponseDto(boolean processCompleted, boolean thumbAct, int boardUpCount) {
        this.processCompleted = processCompleted;
        this.thumbAct = thumbAct;
        this.boardUpCount = boardUpCount;
    }
}
