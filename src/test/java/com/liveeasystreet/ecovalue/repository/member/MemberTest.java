package com.liveeasystreet.ecovalue.repository.member;

import com.liveeasystreet.ecovalue.cond.member.MemberSearchCond;
import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MemberUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Transactional
@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원저장 테스트")
    public void save() {
        
        // member 객체 생성 + 초기화 진행 
        Member member = new Member();
        member.setLoginId("test11");
        member.setMemberPassword("test11");
        member.setNickName("테스트");
        member.setMemberName("테스트");
        member.setEmail("test@naver.com");

        // member 객체 db 에 저장
        memberRepository.save(member);
        log.info("member = {}", member);
        
        // db 에서 memberId 로 객체 가져오기
        Member findMember = memberRepository.findById(member.getMemberId()).orElse(null);
        
        // 멤버의 가입일은 db 저장 시점에 생성 되기때문에 기존 객체에 저장 된 객체의 시간을 저장
        member.setJoinDate(findMember.getJoinDate());
        
        // 저장 전 객체와 저장해서 꺼낸 객체가 같은지 확인
        // Member 가 hashcode equal 가 재정의 돼 있기때문에 같은 값을 가지는지 확인 가능
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    @DisplayName("회원정보 업데이트 테스트")
    public void update() {

        // member 객체 생성 + 초기화 진행 
        Member member = new Member();
        member.setLoginId("test222");
        member.setMemberPassword("test222");
        member.setNickName("테스트222");
        member.setMemberName("테스트");
        member.setEmail("test222@naver.com");

        // member 객체 db에 저장
        memberRepository.save(member);
        log.info("member = {}",member);
        
        // memberUpdateDto 를 생성해 변경 될 값 세팅 
        MemberUpdateDto memberUpdateDto = new MemberUpdateDto();
        memberUpdateDto.setNickName("test223");
        memberUpdateDto.setMemberName("test223");
        memberUpdateDto.setMemberPassword("test223");
        memberUpdateDto.setEmail("test222@naver.com");

        // db에 memberUpdateDto 내용을 반영 
        memberRepository.update(member.getMemberId(), memberUpdateDto);

        // db 에서 수정 된 updateMember 가져오기
        Member updateMember = memberRepository.findById(member.getMemberId()).orElse(null);
        log.info("updateMember = {}",updateMember);

        // db 에서 수정 후 가져 온 updateMember 의 데이터가 수정 잘 됐는지 검증
        Assertions.assertThat(updateMember.getMemberName()).isEqualTo("test223");
        Assertions.assertThat(updateMember.getNickName()).isEqualTo("test223");
        Assertions.assertThat(updateMember.getMemberPassword()).isEqualTo("test223");
        Assertions.assertThat(updateMember.getEmail()).isEqualTo("test222@naver.com");
    }


    @Test
    @DisplayName("회원정보 동적 쿼리 테스트")
    public void findAll() {

        // member 총 3명의 객체 생성 + 초기화 진행
        Member member1 = new Member();
        member1.setLoginId("member1");
        member1.setMemberPassword("member1");
        member1.setNickName("member1");
        member1.setMemberName("member1");
        member1.setEmail("member1@naver.com");
        memberRepository.save(member1);

        // member 총 3명의 객체 생성 + 초기화 진행
        Member member2 = new Member();
        member2.setLoginId("member2");
        member2.setMemberPassword("member2");
        member2.setNickName("member2");
        member2.setMemberName("member2");
        member2.setEmail("member2@naver.com");
        memberRepository.save(member2);

        // member 총 3명의 객체 생성 + 초기화 진행
        Member member3 = new Member();
        member3.setLoginId("member3");
        member3.setMemberPassword("member3");
        member3.setNickName("member3");
        member3.setMemberName("member3");
        member3.setEmail("member3@naver.com");
        memberRepository.save(member3);

        // MemberSearchCond 에 조회에 사용 될 데이터를 담는다
        MemberSearchCond memberSearchCond1 = new MemberSearchCond("member3","","","");
        log.info("memberSearchCond1 = {}",memberSearchCond1);

        // db 에서 MemberSearchCond 의 내용으로 동적쿼리 실행
        List<Member> allV1 = memberRepository.findAll(memberSearchCond1);
        log.info("allV1 = {}",allV1);

        // 찾으려는 값이 loginId member3 하나기때문에 결과 값 1개만 나와야 함
        Assertions.assertThat(allV1.size()).isEqualTo(1);

        // MemberSearchCond 에 조회에 사용 될 데이터를 담는다
        MemberSearchCond memberSearchCond2 = new MemberSearchCond("","member","","");
        List<Member> allV2 = memberRepository.findAll(memberSearchCond2);
        log.info("allV2 = {}",allV2);

        // 찾으려는 값이 nicName 에 member 포함된 값을 찾기때문에 결과 값 3개가 나와야 함
        Assertions.assertThat(allV2.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("회원삭제 테스트")
    public void delete() {

        // 회원 삭제에 사용 될 member 객체 3개를 생성 및 초기화
        Member member4 = new Member();
        member4.setLoginId("member4");
        member4.setMemberPassword("member4");
        member4.setNickName("member4");
        member4.setMemberName("member4");
        member4.setEmail("member4@naver.com");
        memberRepository.save(member4);

        // 회원 삭제에 사용 될 member 객체 3개를 생성 및 초기화
        Member member5 = new Member();
        member5.setLoginId("member5");
        member5.setMemberPassword("member5");
        member5.setNickName("member5");
        member5.setMemberName("member5");
        member5.setEmail("member5@naver.com");
        memberRepository.save(member5);

        // 회원 삭제에 사용 될 member 객체 3개를 생성 및 초기화
        Member member6 = new Member();
        member6.setLoginId("member6");
        member6.setMemberPassword("member6");
        member6.setNickName("member6");
        member6.setMemberName("member6");
        member6.setEmail("member6@naver.com");
        memberRepository.save(member6);

        // member5 id 로 삭제를 진행
        memberRepository.deleteById(member5.getMemberId());

        // db 에서 member5 를 찾을 시 null 을 반환해야 됨
        Member deleteMember = memberRepository.findById(member5.getMemberId()).orElse(null);
        Assertions.assertThat(deleteMember).isNull();

        // db 에 남아있는 객체의 수가 2가 맞는지 확인 하기 위함 모든값 꺼내는 로직 실행
        MemberSearchCond memberSearchCond = new MemberSearchCond();
        List<Member> all = memberRepository.findAll(memberSearchCond);
        Assertions.assertThat(all.size()).isEqualTo(2);

    }
}
