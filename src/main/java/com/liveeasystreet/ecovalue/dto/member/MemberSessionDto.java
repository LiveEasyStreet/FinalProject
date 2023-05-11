package com.liveeasystreet.ecovalue.dto.member;


import com.liveeasystreet.ecovalue.domain.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSessionDto {

    private String loginId;
    private String memberName;
    private int memberPoint;

    public MemberSessionDto() {

    }

    public MemberSessionDto(Member member) {
        this.loginId = member.getLoginId();
        this.memberName = member.getMemberName();
        this.memberPoint = member.getMemberPoint();
    }
}
