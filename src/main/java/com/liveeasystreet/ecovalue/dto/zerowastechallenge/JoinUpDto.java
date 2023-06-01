package com.liveeasystreet.ecovalue.dto.zerowastechallenge;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinUpDto {

    //참여할 챌린지 아이디
    private Long challengeId;

    //참여할 회원 아이디
    private Long memberId;

    public JoinUpDto(Long challengeId, Long memberId) {
        this.challengeId = challengeId;
        this.memberId = memberId;
    }
}
