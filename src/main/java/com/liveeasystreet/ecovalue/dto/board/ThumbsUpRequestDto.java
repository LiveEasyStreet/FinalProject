package com.liveeasystreet.ecovalue.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ThumbsUpRequestDto {

    // 추천 누른 게시물
    private Long boardId;

    // 추천 누른 멤버
    private String  loginId;

    public ThumbsUpRequestDto(Long boardId, String loginId) {
        this.boardId = boardId;
        this.loginId = loginId;
    }
}
