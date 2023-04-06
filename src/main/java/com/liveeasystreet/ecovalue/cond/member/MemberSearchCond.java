package com.liveeasystreet.ecovalue.cond.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class MemberSearchCond {

    // 가입시 입력하는 로그인 아이디
    private String loginId;
    // 닉네임
    private String nickName;
    // 이름
    private String memberName;
    // 이메일
    private String email;

    public MemberSearchCond(){

    }

    public MemberSearchCond(String loginId, String nickName, String memberName, String email) {
        this.loginId = loginId;
        this.nickName = nickName;
        this.memberName = memberName;
        this.email = email;
    }
}
