package com.liveeasystreet.ecovalue.dto.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberUpdateDto {

    private String nickName;

    private String memberName;

    private String memberPassword;

    private String memberPasswordValidate;

    private String email;

}
