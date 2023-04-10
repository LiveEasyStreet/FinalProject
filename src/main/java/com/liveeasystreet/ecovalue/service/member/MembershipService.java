package com.liveeasystreet.ecovalue.service.member;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MembershipDto;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MemberRepository memberRepository;

    /**
     * 회원 저장 메서드<hr>
     * 클라이언트가 입력한 정보를 리포지토리에 저장
     * @param membershipDto
     */
    public void addMember(Member member) {
        memberRepository.save(member);
    }

    /**
     * loginId 중복체크 메서드<hr>
     * 회원가입 중 입력한 아이디가 리포지토리에 있다면 true,<br>
     * 없다면 false 를 반환
     * @param membershipDto
     * @return true or false
     */
    public boolean checkLoginId(MembershipDto membershipDto) {
        return memberRepository.findByLoginId(membershipDto.getLoginId()).isPresent();
    }

    /**
     * nickName 중복체크 메서드<hr>
     * 회원가입 중 입력한 닉네임이 리포지토리에 있다면 true,<br>
     * 없다면 false 를 반환
     * @param membershipDto
     * @return true or false
     */
    public boolean checkNickName(MembershipDto membershipDto) {
        return memberRepository.findByNickName(membershipDto.getNickName()).isPresent();
    }

    /**
     * email 중복체크 메서드<hr>
     * 회원가입 중 입력한 이메일이 리포지토리에 있다면 true,<br>
     * 없다면 false 를 반환
     * @param membershipDto
     * @return true or false
     */
    public boolean checkEmail(MembershipDto membershipDto) {
        return memberRepository.findByEmail(membershipDto.getEmail()).isPresent();
    }

    /**
     * password 확인 메서드<hr>
     * 회원가입 중 입력한 비밀번호, 비밀번호 확인이 같다면 true,<br>
     * 다르다면 false 를 반환
     * @param membershipDto
     * @return true or false
     */
    public boolean checkPassword(MembershipDto membershipDto) {
        return membershipDto.getMemberPassword().equals(membershipDto.getCheckMemberPassword());
    }
}
