package com.liveeasystreet.ecovalue.service.zerowaste;

import com.liveeasystreet.ecovalue.domain.ZeroWasteChallenge;
import com.liveeasystreet.ecovalue.dto.zerowastechallenge.ChallengeViewDto;
import com.liveeasystreet.ecovalue.repository.zerowaste.ZeroWasteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ZeroWasteServiceImpl implements IZeroWasteService {

    private final ZeroWasteRepository zeroWasteRepository;

    @Override
    public ZeroWasteChallenge joinChallengeCount(Long challengeId) {
        zeroWasteRepository.joinMemberCount(challengeId);
        return zeroWasteRepository.findById(challengeId).orElse(null);
    }

    @Override
    public List<ChallengeViewDto> findContinueChallenge() {
        List<ZeroWasteChallenge> challenges = zeroWasteRepository.findAll();
        List<ChallengeViewDto> continueChallenges = new ArrayList<>();
        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy.MM.dd"));

        for (ZeroWasteChallenge challenge : challenges) {
            ChallengeViewDto challengeViewDto = new ChallengeViewDto(challenge);
            int compare = challengeViewDto.getEndChallenge().compareTo(nowDate);

            if (compare >= 0) {
                continueChallenges.add(challengeViewDto);
            }
        }

        return continueChallenges;
    }

    @Override
    public List<ChallengeViewDto> findEndChallenge() {
        List<ZeroWasteChallenge> challenges = zeroWasteRepository.findAll();
        List<ChallengeViewDto> endChallenges = new ArrayList<>();
        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy.MM.dd"));

        for (ZeroWasteChallenge challenge : challenges) {
            ChallengeViewDto challengeViewDto = new ChallengeViewDto(challenge);
            int compare = challengeViewDto.getEndChallenge().compareTo(nowDate);

            if (compare < 0) {
                endChallenges.add(challengeViewDto);
            }
        }

        return endChallenges;
    }
}
