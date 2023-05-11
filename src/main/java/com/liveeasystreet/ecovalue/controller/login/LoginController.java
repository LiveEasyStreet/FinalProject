package com.liveeasystreet.ecovalue.controller.login;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MemberLoginDto;
import com.liveeasystreet.ecovalue.dto.member.MemberSessionDto;
import com.liveeasystreet.ecovalue.service.member.MemberLoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberLoginService memberLoginService;

    @GetMapping("/login")
    public String join(@ModelAttribute("memberLoginDto") MemberLoginDto memberLoginDto,HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            return "redirect:/";
        }

        return "ecovalue/login/login";
    }

    @PostMapping("/login")
    public String join(@Validated @ModelAttribute MemberLoginDto memberLoginDto,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "/") String redirectURL,
                       HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.info("login error = {}", bindingResult);
            return "ecovalue/login/login";
        }

        Member member = memberLoginService.login(memberLoginDto.getLoginId(), memberLoginDto.getMemberPassword());
        log.info("loginMember = {}", member);

        if (member == null) {
            bindingResult.reject("loginError", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "ecovalue/login/login";
        }

        MemberSessionDto memberSession = new MemberSessionDto(member);
        log.info("memberSession = {}", memberSession);

        HttpSession session = request.getSession();
        log.info("session = {}", session.getId());

        session.setAttribute(SessionConst.MEMBER_LOGIN, memberSession);

        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

}
