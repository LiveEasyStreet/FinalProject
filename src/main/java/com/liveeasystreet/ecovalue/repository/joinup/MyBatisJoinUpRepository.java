package com.liveeasystreet.ecovalue.repository.joinup;

import com.liveeasystreet.ecovalue.domain.JoinUp;
import com.liveeasystreet.ecovalue.dto.zerowastechallenge.JoinUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisJoinUpRepository implements JoinUpRepository {

    private final ZeroWasteChallengeJoinUpMapper joinUpMapper;

    @Override
    public void joinUp(JoinUpDto joinUpDto) {
        joinUpMapper.joinUp(joinUpDto);
    }

    @Override
    public Optional<JoinUp> isJoined(JoinUpDto joinUpDto) {
        return joinUpMapper.isJoined(joinUpDto);
    }

    @Override
    public int updateJoinUp(JoinUpDto joinUpDto) {
        return joinUpMapper.updateJoinUp(joinUpDto);
    }

    @Override
    public void deleteJoin(JoinUpDto joinUpDto) {
        joinUpMapper.deleteJoin(joinUpDto);
    }
}
