package com.liveeasystreet.ecovalue.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoinUp {

    private Long challengeId;

    private Long memberId;

    private LocalDateTime dateTime;

    private boolean isDeleted;

    public JoinUp(Long challengeId, Long memberId) {
        this.challengeId = challengeId;
        this.memberId = memberId;
    }
}
