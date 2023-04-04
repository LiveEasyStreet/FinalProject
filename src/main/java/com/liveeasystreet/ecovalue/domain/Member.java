package com.liveeasystreet.ecovalue.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Member {

    // 내부 관리용 아이디
    private int memberId;

    // 가입시 입력하는 로그인 아이디
    private String loginId;
    // 패스워드
    private String Password;
    // 닉네임
    private String nickname;
    // 이름
    private String name;
    // 이메일
    private String email;
    // 가입일
    private LocalDateTime joinDate;
    // 블랙리스트 여부
    private boolean blackList;

}
