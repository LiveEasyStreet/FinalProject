package com.liveeasystreet.ecovalue.domain;

import com.liveeasystreet.ecovalue.dto.member.MemberUpdateDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {

    // 내부 관리용 아이디
    private Long memberId;

    // 가입시 입력하는 로그인 아이디
    private String loginId;
    // 패스워드
    private String memberPassword;
    // 닉네임
    private String nickName;
    // 이름
    private String memberName;
    // 이메일
    private String email;
    // 가입일
    private LocalDateTime joinDate;
    // 블랙리스트 여부
    private String blackList;
    // 포인트
    private int memberPoint;

    public Member() {

    }

    public Member(MemberUpdateDto updateDTO) {

    }

}
