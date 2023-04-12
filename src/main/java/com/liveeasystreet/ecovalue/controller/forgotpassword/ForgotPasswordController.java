package com.liveeasystreet.ecovalue.controller.forgotpassword;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.service.member.MemberFindIdPasswordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ForgotPasswordController {

    /**
     * email 만 입력하면 해당 이메일로 가입돼 있는 아이디 값 찾아주는 기능
     */

    private final MemberFindIdPasswordService memberFindIdPasswordService;

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "ecovalue/forgot-password/forgot-password";
    } // End forgotPassword


    /**
     * 리다이렉트만들 실행하는 메소드
     *
     * @param email
     * @param redirectAttributes
     * @throws IOException
     */

    @PostMapping("/forgot-email")
    public String redirect2Method(@RequestParam(name = "email") String email, RedirectAttributes redirectAttributes) {

        Member member = memberFindIdPasswordService.checkEmail(email);

        if (member == null) {
            redirectAttributes.addFlashAttribute("notFoundMember", true);
            return "redirect:/forgot-password";
        }

        log.info("member => {}", member);
        redirectAttributes.addFlashAttribute("foundMember", member);
        return "redirect:/forgot-email-result";
    }

    /**
     * 임시적으로 temp.html을 만들어서 제대로 동작하는지 확인
     *
     * @param member
     * @return forgot-password-result.html
     */
    @GetMapping("/forgot-email-result")
    public String temp(@ModelAttribute(name = "foundMember") Member member) {
        log.info("foundMember=> {}", member);
        return "ecovalue/forgot-password/forgot-password-result";
    } // End temp
} // End Class
