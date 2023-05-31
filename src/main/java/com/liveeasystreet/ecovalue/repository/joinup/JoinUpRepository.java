package com.liveeasystreet.ecovalue.repository.joinup;

import com.liveeasystreet.ecovalue.domain.JoinUp;
import com.liveeasystreet.ecovalue.dto.zerowastechallenge.JoinUpDto;

import java.util.Optional;

public interface JoinUpRepository {

    void joinUp(JoinUpDto joinUpDto);

    Optional<JoinUp> isJoined(JoinUpDto joinUpDto);

    int updateJoinUp(JoinUpDto joinUpDto);

    void deleteJoin(JoinUpDto joinUpDto);
}
