package com.liveeasystreet.ecovalue.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembershipDto {

    private String loginId;
    private String memberPassword;
    private String checkMemberPassword;
    private String nickName;
    private String memberName;
    private String email;

}
