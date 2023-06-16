package com.liveeasystreet.ecovalue.domain;

import com.liveeasystreet.ecovalue.dto.member.MembershipDto;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
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

    // 우편번호
    private String postCode;
    // 주소
    private String address;
    // 상세 주소
    private String addressDetail;

    // 기본 생성자
    public Member() {
        this.blackList = "N";
        this.memberPoint = 100;
    }

    // 회원가입 dto 를 매개변수로 받는 생성자
    public Member(MembershipDto membershipDto) {
        this();
        this.loginId = membershipDto.getLoginId();
        this.memberPassword = membershipDto.getMemberPassword();
        this.nickName = membershipDto.getNickName();
        this.memberName = membershipDto.getMemberName();
        this.email = membershipDto.getEmail();
        this.postCode = membershipDto.getPostCode();
        this.address = membershipDto.getAddress();
        this.addressDetail = membershipDto.getAddressDetail();
    }

}
