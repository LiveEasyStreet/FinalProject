package com.liveeasystreet.ecovalue.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ZeroWasteChallenge {

    //첼린지 번호
    private Long challengeId;

    //제목
    private String title;

    //카테고리
    private Long boardCategory;

    //내용
    private String contents;

    //시작일
    private LocalDateTime startChallenge;

    //종료일
    private LocalDateTime endChallenge;

    //챌린지 참여인원
    private int joinMembers;

    public ZeroWasteChallenge() {
    }

    public ZeroWasteChallenge(Long challengeId, String title, Long boardCategory, String contents, LocalDateTime startChallenge, LocalDateTime endChallenge, int joinMembers) {
        this.challengeId = challengeId;
        this.title = title;
        this.boardCategory = boardCategory;
        this.contents = contents;
        this.startChallenge = startChallenge;
        this.endChallenge = endChallenge;
        this.joinMembers = joinMembers;
    }
}
