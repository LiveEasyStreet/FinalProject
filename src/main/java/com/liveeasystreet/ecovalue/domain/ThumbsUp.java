package com.liveeasystreet.ecovalue.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ThumbsUp {

    // 추천 누른 게시물
    private Long boardId;

    // 추천 누른 멤버
    private Long memberId;

    // 추천 시간
    private LocalDateTime dateTime;

    // 취소 여부
    private boolean isDeleted;

    public ThumbsUp(Long boardId, Long memberId) {
        this.boardId = boardId;
        this.memberId = memberId;
    }
}
