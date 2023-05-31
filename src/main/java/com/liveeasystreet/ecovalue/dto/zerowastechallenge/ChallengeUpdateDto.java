package com.liveeasystreet.ecovalue.dto.zerowastechallenge;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChallengeUpdateDto {

    //제목
    private String title;

    //내용
    private String contents;

    //종료일
    private LocalDateTime endChallenge;
}
