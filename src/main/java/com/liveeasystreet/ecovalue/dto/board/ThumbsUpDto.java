package com.liveeasystreet.ecovalue.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThumbsUpDto {

    // 추천 누른 게시물
    private Long boardId;

    // 추천 누른 멤버
    private Long memberId;

    public ThumbsUpDto(Long boardId, Long memberId) {
        this.boardId = boardId;
        this.memberId = memberId;
    }
}
