package com.liveeasystreet.ecovalue.dto.member;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MembershipDto {

    @NotBlank
    @Size(min = 3, max = 12)
    private String loginId;
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    private String memberPassword;
    @NotBlank
    private String checkMemberPassword;
    @NotBlank
    @Size(min = 1,max = 12)
    private String nickName;
    @NotBlank
    @Pattern(regexp = "^[가-힣]*$")
    private String memberName;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$")
    private String email;

    private String recaptcha;
}
