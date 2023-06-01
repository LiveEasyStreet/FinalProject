package com.liveeasystreet.ecovalue.service.zerowaste;

import com.liveeasystreet.ecovalue.domain.ZeroWasteChallenge;
import com.liveeasystreet.ecovalue.dto.zerowastechallenge.ChallengeViewDto;

import java.util.List;

public interface IZeroWasteService {

    ZeroWasteChallenge joinChallengeCount(Long challengeId);

    List<ChallengeViewDto> findContinueChallenge();

    List<ChallengeViewDto> findEndChallenge();
}
