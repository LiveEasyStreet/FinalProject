package com.liveeasystreet.ecovalue.repository.member;

import com.liveeasystreet.ecovalue.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional
@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원저장 테스트")
    public void save(){
        Member member = new Member();

        member.setLoginId("test11");
        member.setMemberPassword("test11");
        member.setNickName("테스트");
        member.setMemberName("테스트");
        member.setEmail("test@naver.com");
        member.setBlackList("N");
        member.setMemberPoint(0);

        memberRepository.save(member);
        log.info("member = {}",member);
        Member findMember = memberRepository.findById(member.getMemberId()).orElse(null);
        member.setJoinDate(findMember.getJoinDate());
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
