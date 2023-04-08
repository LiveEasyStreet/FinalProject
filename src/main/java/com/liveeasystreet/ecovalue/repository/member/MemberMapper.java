package com.liveeasystreet.ecovalue.repository.member;

import com.liveeasystreet.ecovalue.cond.member.MemberSearchCond;
import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MemberUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


@Mapper
public interface MemberMapper {

    void save(Member member);
    void update(@Param("id") Long id, @Param("updateDto") MemberUpdateDto updateDto);

    Optional<Member> findById(Long id);

    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByNickName(String nickName);

    Optional<Member> findByEmail(String email);

    List<Member> findAll(MemberSearchCond memberSearchCond);

    void deleteById(Long id);

}
