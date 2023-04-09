package com.liveeasystreet.ecovalue.controller.membership;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MembershipDto;
import com.liveeasystreet.ecovalue.service.member.MembershipService;
import com.liveeasystreet.ecovalue.validator.member.MembershipValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipService membershipService;
    private final MembershipValidator membershipValidator;

    @GetMapping("/add")
    public String membershipHome(@ModelAttribute("membershipDto") MembershipDto membershipDto){
        return "ecovalue/membership/membership";
    }

    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute MembershipDto membershipDto,
                            BindingResult bindingResult) {
        //검증 로직
        membershipValidator.validate(membershipDto, bindingResult);

        //검증에 실패하면 다시 회원가입 페이지로 이동
        if (bindingResult.hasErrors()) {
            return "ecovalue/membership/membership";
        }

        //성공 로직
        membershipService.addMember(membershipDto);

        return "redirect:ecovalue/ecovalue-home";
    }

}
