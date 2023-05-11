package com.liveeasystreet.ecovalue.controller.manager;

import com.liveeasystreet.ecovalue.controller.login.SessionConst;
import com.liveeasystreet.ecovalue.domain.Manager;
import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.manager.ManagerLoginDto;
import com.liveeasystreet.ecovalue.dto.manager.ManagerSessionDto;
import com.liveeasystreet.ecovalue.dto.member.MemberSessionDto;
import com.liveeasystreet.ecovalue.service.manager.ManagerLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
@Slf4j
public class ManagerController {

    private final ManagerLoginService managerLoginService;

    @GetMapping("/home")
    public String home(@SessionAttribute(name = SessionConst.MANAGER_LOGIN,required = true) ManagerSessionDto loginManager, Model model) {

        log.info("[{}]",loginManager);
        if (loginManager!=null){
            model.addAttribute("manager",loginManager);
        }
        return "manager/home/manager-home";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("managerLoginDto")ManagerLoginDto managerLoginDto){
        return "manager/login/manager-login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute ManagerLoginDto managerLoginDto,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/manager/home") String redirectURL,
                        HttpServletRequest request
                        ){
        if (bindingResult.hasErrors()) {
            log.info("Manager login error = {}", bindingResult);
            return "manager/login/manager-login";
        }

        Manager manager = managerLoginService.login(managerLoginDto.getManagerLoginId(), managerLoginDto.getManagerPassword(),managerLoginDto.getSecondManagerPassword());
        log.info("loginManager = {}", manager);

        if (manager == null) {
            bindingResult.reject("managerLoginError", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "manager/login/manager-login";
        }

        ManagerSessionDto managerSession = new ManagerSessionDto(manager);
        log.info("memberSession = {}", managerSession);

        HttpSession session = request.getSession();
        log.info("session = {}", session.getId());

        session.setAttribute(SessionConst.MANAGER_LOGIN, managerSession);

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
