package com.liveeasystreet.ecovalue.service.member;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberRepository memberRepository;

    /**
     * 로그인 메서드<hr>
     * 매개변수로 받는 loginId 로 리포지토리에서 조회,<br>
     * member 가 있다면 member 의  password 가 맞는지 확인 후 member 를 반환<br>
     * 만약 조회가 되지 않는다면 null 을 반환
     * @param loginId
     * @param password
     * @return member or null
     */
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getMemberPassword().equals(password))
                .orElse(null);
    }
}
