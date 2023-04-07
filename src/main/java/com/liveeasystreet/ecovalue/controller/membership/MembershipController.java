package com.liveeasystreet.ecovalue.controller.membership;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MembershipDTO;
import com.liveeasystreet.ecovalue.service.member.MembershipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping("addMember")
    public String membershipHome(@ModelAttribute("membershipDTO") MembershipDTO membershipDTO){
        return "membership/membership";
    }

    @PostMapping("addMember")
    public String addMember(@Validated @ModelAttribute MembershipDTO membershipDTO,
                            BindingResult bindingResult) {

        //검증에 실패하면 다시 입력 폼으로 이동
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "membership/membership";
        }

        //중복 체크 로직

        //아이디 체크

        //비밀번호 체크
        if (membershipDTO.getMemberPassword() == membershipDTO.getCheckPassword()) {
            bindingResult.rejectValue("checkPassword", "", null);
        }

        //이메일

        //별명

        //성공 로직
        membershipService.addMember(membershipDTO);

        return "ecovalue/ecovalue-home";
    }

}
