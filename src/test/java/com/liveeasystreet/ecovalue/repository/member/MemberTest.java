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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * <strong>회원 리포지토리 테스트 클래스. </strong><br>
 * 해당 테스트 클래스에서는 테스트 메소드의 순서를 지정하지 않았기 때문에 <br>
 * 각 member객체 구분을 확실하게 하고자 객체명을 'member+N'으로 생성함.
 */
@Slf4j
@Transactional
@SpringBootTest
public class MemberTest {

    /**
     * MemberRepository 클래스를 테스트 하는 클래스이므로, 의존성을 주입함. <br>
     * @Autowired : 필요한 의존 객체의 “타입"에 해당하는 Bean을 찾아 주입하는 어노테이션. <br>
     *              기본값이 true이기 때문에 의존성 주입을 할 대상을 찾지 못한다면 애플리케이션 구동에 실패함.
     */
    @Autowired

    MemberRepository memberRepository;

    /**
     * <strong>회원가입 성공 테스트 메소드.</strong><br>
     * 객체를 생성하여 db에 저장 후, memberId로 객체를 불러와 저장된 객체와 <br>
     * 저장 후 객체가 같은 지 확인함.<br>
     * 로직을 수행했을 때의 기대값은 테스트 성공이다.
     */
    @Test
    @DisplayName("회원가입 테스트")
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

    /**
     * <strong>회원가입 실패 테스트 메소드.</strong><br>
     * 대조군의 객체(member1)를 생성 및 초기화 진행 후 db에 저장함.<br>
     * 실험군의 객체(member2)를 생성 및 초기화 진행 시 닉네임을 member1과 중복되도록 넣어줌.<br>
     * member2 객체를 db에 저장 시 닉네임이 중복되므로 DuplicateKeyException이 발생함. <br>
     * 로직을 수행했을 때의 기대값은 테스트 성공이다.
     */
    @Test
    @DisplayName("회원가입 실패 테스트")
    public void saveFail () {

        // Member 객체 생성 + 초기화 진행
        Member member1 = new Member();
        member1.setLoginId("test111");
        member1.setMemberPassword("test111");
        member1.setNickName("테스트111");
        member1.setMemberName("테스트111");
        member1.setEmail("test111@naver.com");

        // member 객체 db 에 저장
        memberRepository.save(member1);
        log.info("member1 = {}", member1);

        // Member 객체 생성 + 초기화 진행
        Member member2 = new Member();
        member2.setLoginId("test112");
        member2.setMemberPassword("test112");
        member2.setNickName("테스트111");
        member2.setMemberName("테스트112");
        member2.setEmail("test112@naver.com");

        // 닉네임이 중복되었기 때문에 예외가 발생
        assertThrows(DuplicateKeyException.class, () -> memberRepository.save(member2));
    }

    /**
     * <strong>회원 수정 테스트 메소드.</strong><br>
     * 객체를 생성 및 초기화 진행 후 db에 저장하고,<br>
     * memberUpdateDto를 생성하여 변경 될 값을 넣어주고 db에 해당 내용을 반영함.<br>
     * memberId를 통해 수정된 회원을 불러 온 뒤, 데이터가 잘 수정되었는지 확인함. <br>
     * 로직을 수행했을 때의 기대값은 테스트 성공이다.
     */
    @Test
    @DisplayName("회원 수정 테스트")
    public void update() {

        // member 객체 생성 + 초기화 진행
        Member member3 = new Member();
        member3.setLoginId("test222");
        member3.setMemberPassword("test222");
        member3.setNickName("테스트222");
        member3.setMemberName("테스트");
        member3.setEmail("test222@naver.com");

        // member 객체 db에 저장
        memberRepository.save(member3);
        log.info("member3 = {}",member3);

        // memberUpdateDto 를 생성해 변경 될 값 세팅
        MemberUpdateDto memberUpdateDto = new MemberUpdateDto();
        memberUpdateDto.setNickName("test223");
        memberUpdateDto.setMemberName("test223");
        memberUpdateDto.setMemberPassword("test223");
        memberUpdateDto.setEmail("test222@naver.com");

        // db에 memberUpdateDto 내용을 반영
        memberRepository.update(member3.getMemberId(), memberUpdateDto);

        // db 에서 수정 된 updateMember 가져오기
        Member updateMember = memberRepository.findById(member3.getMemberId()).orElse(null);
        log.info("updateMember = {}",updateMember);

        // db 에서 수정 후 가져 온 updateMember 의 데이터가 수정 잘 됐는지 검증
        Assertions.assertThat(updateMember.getMemberName()).isEqualTo("test223");
        Assertions.assertThat(updateMember.getNickName()).isEqualTo("test223");
        Assertions.assertThat(updateMember.getMemberPassword()).isEqualTo("test223");
        Assertions.assertThat(updateMember.getEmail()).isEqualTo("test222@naver.com");
    }

    /**
     * <strong>회원 수정 실패 테스트.</strong><br>
     * 실험군의 객체(member4)와 대조군의 객체(member5)생성 및 초기화 진행 후 db에 저장함.<br>
     * memberUpdateDto를 생성하여 값을 넣을 때 이메일 데이터 중복 시의 결과를 보기 위해,<br>
     * 이메일만 member5와 중복되게 넣어 주고, db에 memberUpdateDto를 반영함.<br>
     * 반영하려고 했을 때, 이메일이 중복되므로 DuplicateKeyException이 발생함.<br>
     * 로직을 수행했을 때의 기대값은 테스트 성공이다.
     */
    @Test
    @DisplayName("회원 수정 실패 테스트")
    public void updateFail() {
        // member 객체 생성 + 초기화 진행
        Member member4 = new Member();
        member4.setLoginId("test333");
        member4.setMemberPassword("test333");
        member4.setNickName("테스트333");
        member4.setMemberName("테스트333");
        member4.setEmail("test333@naver.com");

        // member 객체 db에 저장
        memberRepository.save(member4);
        log.info("member4 = {}",member4);

        // member 객체 생성 + 초기화 진행
        Member member5 = new Member();
        member5.setLoginId("test334");
        member5.setMemberPassword("test334");
        member5.setNickName("테스트334");
        member5.setMemberName("테스트334");
        member5.setEmail("test334@naver.com");

        // member 객체 db에 저장
        memberRepository.save(member5);
        log.info("member5 = {}",member5);


        // memberUpdateDto 를 생성해 변경 될 값 세팅
        MemberUpdateDto memberUpdateDto = new MemberUpdateDto();
        memberUpdateDto.setNickName("test335");
        memberUpdateDto.setMemberName("test335");
        memberUpdateDto.setMemberPassword("test335");
        memberUpdateDto.setEmail("test334@naver.com");

        // db에 memberUpdateDto 내용을 반영했을 때 이메일이 중복되어 예외 발생
        assertThrows(DuplicateKeyException.class, () -> memberRepository.update(member4.getMemberId(), memberUpdateDto));

    }

    /**
     * <strong>회원정보 동적 쿼리 실행 테스트.</strong><br>
     * loginId와 nickName을 검색 조건으로 테스트 수행함.<br>
     * 총 3명의 객체를 생성 및 초기화 진행 후 db에 저장함.<br>
     * MemberSearchCond를 생성하여 조회에 사용할 데이터를 넣고, db에서 해당 조건으로 전체 조회를 실행함.<br>
     * 1.MemberSearchCond에 넣은 값이 loginId : member8이기 때문에, 결과값은 1이 나와야 함.<br>
     * 2. MemberSearchCond에 넣은 값이 nickName : member이기 때문에, 닉네임에 member가 포함된 객체의 개수 3이 나옴.<br>
     * 로직을 수행했을 때의 기대값은 테스트 성공이다.
     */
    @Test
    @DisplayName("회원정보 동적 쿼리 실행 테스트")
    public void findAll() {

        // member 총 3명의 객체 생성 + 초기화 진행
        Member member6 = new Member();
        member6.setLoginId("member6");
        member6.setMemberPassword("member6");
        member6.setNickName("member6");
        member6.setMemberName("member6");
        member6.setEmail("member6@naver.com");
        memberRepository.save(member6);

        // member 총 3명의 객체 생성 + 초기화 진행
        Member member7 = new Member();
        member7.setLoginId("member7");
        member7.setMemberPassword("member7");
        member7.setNickName("member7");
        member7.setMemberName("member7");
        member7.setEmail("member7@naver.com");
        memberRepository.save(member7);

        // member 총 3명의 객체 생성 + 초기화 진행
        Member member8 = new Member();
        member8.setLoginId("member8");
        member8.setMemberPassword("member8");
        member8.setNickName("member8");
        member8.setMemberName("member8");
        member8.setEmail("member8@naver.com");
        memberRepository.save(member8);

        // MemberSearchCond 에 조회에 사용 될 데이터를 담는다
        MemberSearchCond memberSearchCond1 = new MemberSearchCond("member8","","","");
        log.info("memberSearchCond1 = {}",memberSearchCond1);

        // db 에서 MemberSearchCond 의 내용으로 동적쿼리 실행
        List<Member> allV1 = memberRepository.findAll(memberSearchCond1);
        log.info("allV1 = {}",allV1);

        // 찾으려는 값이 loginId member8 하나이기 때문에 결과 값 1개만 나와야 함
        Assertions.assertThat(allV1.size()).isEqualTo(1);

        // MemberSearchCond 에 조회에 사용 될 데이터를 담는다
        MemberSearchCond memberSearchCond2 = new MemberSearchCond("","member","","");
        List<Member> allV2 = memberRepository.findAll(memberSearchCond2);
        log.info("allV2 = {}",allV2);

        // 찾으려는 값이 nicName 에 member 포함된 값을 찾기때문에 결과 값 3개가 나와야 함
        Assertions.assertThat(allV2.size()).isEqualTo(3);
    }

    /**
     * <strong>회원 삭제 테스트 메소드.</strong><br>
     * 3개의 객체를 생성 및 초기화 진행 후, member9의 Id로 삭제를 진행함.<br>
     * db에서 Id로 member9를 찾을 시 삭제되었기 때문에 null을 반환함. <br>
     * 3개의 객체 중 1개를 삭제를 했기 때문에, 잔여 객체 수가 2가 맞는지 <br>
     * 확인하기 위해 모든 값을 꺼내서 확인함.<br>
     * 로직을 수행했을 때의 기대값은 테스트 성공이다.
     */
    @Test
    @DisplayName("회원 삭제 테스트")
    public void delete() {

        // 회원 삭제에 사용 될 member 객체 3개를 생성 및 초기화
        Member member9 = new Member();
        member9.setLoginId("member9");
        member9.setMemberPassword("member9");
        member9.setNickName("member9");
        member9.setMemberName("member9");
        member9.setEmail("member9@naver.com");
        memberRepository.save(member9);

        // 회원 삭제에 사용 될 member 객체 3개를 생성 및 초기화
        Member member10 = new Member();
        member10.setLoginId("member10");
        member10.setMemberPassword("member10");
        member10.setNickName("member10");
        member10.setMemberName("member10");
        member10.setEmail("member10@naver.com");
        memberRepository.save(member10);

        // 회원 삭제에 사용 될 member 객체 3개를 생성 및 초기화
        Member member11 = new Member();
        member11.setLoginId("member11");
        member11.setMemberPassword("member11");
        member11.setNickName("member11");
        member11.setMemberName("member11");
        member11.setEmail("member11@naver.com");
        memberRepository.save(member11);

        // member9 id 로 삭제를 진행
        memberRepository.deleteById(member9.getMemberId());

        // db 에서 member9 를 찾을 시 null 을 반환해야 됨
        Member deleteMember = memberRepository.findById(member9.getMemberId()).orElse(null);
        Assertions.assertThat(deleteMember).isNull();

        // db 에 남아있는 객체의 수가 2가 맞는지 확인 하기 위함 모든값 꺼내는 로직 실행
        MemberSearchCond memberSearchCond = new MemberSearchCond();
        List<Member> all = memberRepository.findAll(memberSearchCond);
        Assertions.assertThat(all.size()).isEqualTo(2);

    }
}

