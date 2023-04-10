package com.liveeasystreet.ecovalue.validator.member;

import com.liveeasystreet.ecovalue.dto.member.MembershipDto;
import com.liveeasystreet.ecovalue.service.member.MembershipService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MembershipValidator implements Validator {

    private final MembershipService membershipService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MembershipDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MembershipDto membershipDto = (MembershipDto) target;

        //loginId 중복 검증
        if (membershipService.checkLoginId(membershipDto)) {
            errors.rejectValue("loginId", "duplicateId");
        }

        //nickName 중복 검증
        if (membershipService.checkNickName(membershipDto)) {
            errors.rejectValue("nickName", "duplicateNickName");
        }

        //email 중복 검증
        if (membershipService.checkEmail(membershipDto)) {
            errors.rejectValue("email", "duplicateEmail");
        }

        //memberPassword 검증
        if (membershipService.checkPassword(membershipDto)) {
            errors.rejectValue("checkMemberPassword", "checkPassword");
        }
    }
}
