package com.liveeasystreet.ecovalue.controller.membership;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MembershipDto;
import com.liveeasystreet.ecovalue.service.member.MembershipService;
import com.liveeasystreet.ecovalue.validator.member.MembershipValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipService membershipService;
    private final MembershipValidator membershipValidator;

    /**
     * 컨트롤러가 실행될 때 마다 적용되는 검증기<hr>
     * WebDataBinder 는 스프링의 파라미터 바인딩의 역할을 해주고 검증 기능도 내부에 포함한다.<br>
     * WebDataBinder 에 검증기를 추가하면 해당 컨트롤러 안에서 자동으로 적용할 수 있다.<br>
     * InitBinder 어노테이션은 해당 컨트롤러에만 영향을 준다.<br>
     * 검증 할 객체에는 @Validated 또는 @Valid 를 달아주어야 한다.
     * @param dataBinder
     */
    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(membershipValidator);
    }

    @GetMapping("/add")
    public String membershipHome(@ModelAttribute("membershipDto") MembershipDto membershipDto){
        return "ecovalue/membership/membership";
    }

    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute MembershipDto membershipDto,
                            BindingResult bindingResult) {

        //검증에 실패하면 다시 회원가입 페이지로 이동
        if (bindingResult.hasErrors()) {
            log.info("errors ={}", bindingResult);
            return "ecovalue/membership/membership";
        }

        //성공 로직
        Member member = new Member(membershipDto);
        membershipService.addMember(member);

        return "redirect:/";
    }

}
