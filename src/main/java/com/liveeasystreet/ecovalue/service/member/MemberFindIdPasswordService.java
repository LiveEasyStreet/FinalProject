package com.liveeasystreet.ecovalue.service.member;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberFindIdPasswordService {
    private final MemberRepository memberRepository;

    /**
     * 해당 이메일이 있으면 해당 Member 반환
     * 그렇지 않으면 주소 값 : null 반환
     *
     * @return Member
     */

    public Member checkEmail(String email) {
        log.info("checkEmail :: invoked");
        return memberRepository.findByEmail(email).orElse(null);
    } // End checkEmail
} // End Class
