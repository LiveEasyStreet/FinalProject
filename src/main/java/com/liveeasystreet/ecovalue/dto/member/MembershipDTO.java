package com.liveeasystreet.ecovalue.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MembershipDTO {

    @NotBlank
    private String loginId;
    @NotBlank
    private String memberPassword;
    @NotBlank
    private String checkPassword;
    @NotBlank
    private String nickName;
    @NotBlank
    private String memberName;
    @Email
    @NotBlank
    private String email;
}
