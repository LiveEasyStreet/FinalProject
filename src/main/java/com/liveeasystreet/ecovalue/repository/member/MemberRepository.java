package com.liveeasystreet.ecovalue.repository.member;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MemberUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {

    void save(Member member);

    void update(Long id, MemberUpdateDto updateDto);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    void deleteById(Long id);

}
