package com.liveeasystreet.ecovalue.service.member;

import com.liveeasystreet.ecovalue.cond.member.MemberSearchCond;
import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MembershipDTO;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MemberRepository memberRepository;

    /**
     * 회원 등록 메서드<hr>
     * 회원의 정보를 받아 리포지토리에 저장하는 메서드
     * @param membershipDTO
     * @return member
     */
    public Member addMember(MembershipDTO membershipDTO) {
        Member member = new Member();

        member.setLoginId(membershipDTO.getLoginId());
        member.setMemberPassword(membershipDTO.getMemberPassword());
        member.setNickName(membershipDTO.getNickName());
        member.setMemberName(membershipDTO.getMemberName());
        member.setEmail(membershipDTO.getEmail());

        memberRepository.save(member);

        return member;
    }
}
