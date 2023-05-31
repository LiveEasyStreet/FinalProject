package com.liveeasystreet.ecovalue.repository.joinup;

import com.liveeasystreet.ecovalue.domain.JoinUp;
import com.liveeasystreet.ecovalue.dto.zerowastechallenge.JoinUpDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ZeroWasteChallengeJoinUpMapper {

    //참여하기
    void joinUp(JoinUpDto joinUpDto);

    //참여 여부
    Optional<JoinUp> isJoined(JoinUpDto joinUpDto);

    //참여 업데이트
    int updateJoinUp(JoinUpDto joinUpDto);

    //참여 취소
    void deleteJoin(JoinUpDto joinUpDto);
}
