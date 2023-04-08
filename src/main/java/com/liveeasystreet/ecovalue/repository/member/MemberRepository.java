package com.liveeasystreet.ecovalue.repository.member;

import com.liveeasystreet.ecovalue.cond.member.MemberSearchCond;
import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MemberUpdateDto;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {

    void save(Member member);

    void update(Long id, MemberUpdateDto updateDto);

    Optional<Member> findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByNickName(String nickName);

    Optional<Member> findByEmail(String email);

    List<Member> findAll(MemberSearchCond memberSearchCond);

    void deleteById(Long id);

}
