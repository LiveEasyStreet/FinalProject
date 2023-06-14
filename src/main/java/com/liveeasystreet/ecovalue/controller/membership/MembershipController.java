package com.liveeasystreet.ecovalue.controller.membership;

import com.liveeasystreet.ecovalue.RecaptchaConfig;
import com.liveeasystreet.ecovalue.controller.login.SessionConst;
import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.member.MemberSessionDto;
import com.liveeasystreet.ecovalue.dto.member.MembershipDto;
import com.liveeasystreet.ecovalue.dto.member.RecaptchaResponse;
import com.liveeasystreet.ecovalue.service.member.MembershipService;
import com.liveeasystreet.ecovalue.validator.member.MembershipValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipService membershipService;
    private final MembershipValidator membershipValidator;

    private final RecaptchaConfig recaptchaConfig;


    private final RestTemplate restTemplate;
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
    public String membershipHome(@ModelAttribute("membershipDto") MembershipDto membershipDto,
                                 @SessionAttribute(value = SessionConst.MEMBER_LOGIN, required = false) MemberSessionDto memberSessionDto){

        if (membershipDto!=null){
            return "redirect:/";
        }
        return "ecovalue/membership/membership";
    }

    @PostMapping("/add")
    public String addMember(@Validated @ModelAttribute MembershipDto membershipDto,
                            BindingResult bindingResult,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        if (StringUtils.isEmpty(gRecaptchaResponse) || !verifyRecaptcha(gRecaptchaResponse)) {
            bindingResult.rejectValue("recaptcha", "recaptcha.error", "reCAPTCHA verification failed");
        }
//
//        membershipDto.setGRecaptchaResponse(gRecaptchaResponse);
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

    private boolean verifyRecaptcha(String gRecaptchaResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("secret",recaptchaConfig.getSecret());
        map.add("response",gRecaptchaResponse);

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map, headers);
        RecaptchaResponse response =restTemplate.postForObject(recaptchaConfig.getUrl(),request,RecaptchaResponse.class);

        log.info("\nSuccess : {}\nhostName : {}\nchallenge_ts : {}\n",response.isSuccess(),response.getHostname(),response.getChallenge_ts());

        if (response.getErrorCodes() !=null){
            for (String error : response.getErrorCodes()){
                log.info("/t{}\n",error);
            }
        }

        return response.isSuccess();
    }

}
