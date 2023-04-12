package com.liveeasystreet.ecovalue.dto.member;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String memberPassword;
}
