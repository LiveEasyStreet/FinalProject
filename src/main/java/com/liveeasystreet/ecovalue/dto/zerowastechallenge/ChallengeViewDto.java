package com.liveeasystreet.ecovalue.dto.zerowastechallenge;

import com.liveeasystreet.ecovalue.domain.ZeroWasteChallenge;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ChallengeViewDto {

    private final DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yy.MM.dd");

    private String title;

    private String contents;

    private String startChallenge;

    private String endChallenge;

    private int joinMembers;

    public ChallengeViewDto() {
    }

    public ChallengeViewDto(ZeroWasteChallenge challenge) {
        this.title = challenge.getTitle();
        this.contents = challenge.getContents();
        this.startChallenge = challenge.getStartChallenge().format(formatter);
        this.endChallenge = challenge.getEndChallenge().format(formatter);
        this.joinMembers = challenge.getJoinMembers();
    }
}
