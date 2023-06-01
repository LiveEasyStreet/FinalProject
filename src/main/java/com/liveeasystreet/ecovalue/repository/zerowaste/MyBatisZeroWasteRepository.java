package com.liveeasystreet.ecovalue.repository.zerowaste;

import com.liveeasystreet.ecovalue.domain.ZeroWasteChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisZeroWasteRepository implements ZeroWasteRepository {

    private final ZeroWasteMapper zeroWasteMapper;

    @Override
    public void save(ZeroWasteChallenge challenge) {
        zeroWasteMapper.save(challenge);
    }

    @Override
    public Optional<ZeroWasteChallenge> findById(Long challengeId) {
        return zeroWasteMapper.findById(challengeId);
    }

    @Override
    public List<ZeroWasteChallenge> findAll() {
        return zeroWasteMapper.findAll();
    }

    @Override
    public Long joinMemberCount(Long challengeId) {
        return zeroWasteMapper.joinMemberCount(challengeId);
    }

    @Override
    public void deleteById(Long challengeId) {
        zeroWasteMapper.deleteById(challengeId);
    }
}
