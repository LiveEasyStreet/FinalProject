package com.liveeasystreet.ecovalue.service.member;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDataAccessService {
    /**
     * 보안에 위배되지 않을 정도의 멤버 데이터 접근을 위한 서비스
     */
    private final MemberRepository memberRepository;

    public String getMemberNickNameById(Long id){
        Member member = memberRepository.findById(id).orElse(null);
        if (member != null){
            return member.getNickName();
        }else {
            return "(알 수 없는 사용자)";
        }

    }
}
