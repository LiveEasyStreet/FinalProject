package com.liveeasystreet.ecovalue.repository.zerowaste;

import com.liveeasystreet.ecovalue.domain.ZeroWasteChallenge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ZeroWasteMapper {

    //저장
    void save(ZeroWasteChallenge challenge);

    //단일 조회
    Optional<ZeroWasteChallenge> findById(Long challengeId);

    //전체 조회
    List<ZeroWasteChallenge> findAll();

    //참여인원 카운트
    Long joinMemberCount(Long challengeId);

    //삭제
    void deleteById(Long challengeId);
}
