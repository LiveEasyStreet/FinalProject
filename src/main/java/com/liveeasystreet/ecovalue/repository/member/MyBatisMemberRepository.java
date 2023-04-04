package com.liveeasystreet.ecovalue.repository.member;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public void save(Member member) {
        memberMapper.save(member);
    }

    @Override
    public void update(Long id, MemberUpdateDto updateDto) {
        memberMapper.update(id, updateDto);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public void deleteById(Long id) {
        memberMapper.deleteById(id);
    }
}
