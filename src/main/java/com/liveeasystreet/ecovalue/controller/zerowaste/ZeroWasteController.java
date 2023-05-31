package com.liveeasystreet.ecovalue.controller.zerowaste;

import com.liveeasystreet.ecovalue.dto.zerowastechallenge.ChallengeViewDto;
import com.liveeasystreet.ecovalue.service.joinup.IJoinUpService;
import com.liveeasystreet.ecovalue.service.zerowaste.IZeroWasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zerowaste")
@RequiredArgsConstructor
public class ZeroWasteController {

    private final IZeroWasteService zeroWasteService;

    private final IJoinUpService joinUpService;

    @GetMapping("/introduce")
    public String introduce() {
        return "ecovalue/zerowaste/introduce";
    }

    @GetMapping("/challenge")
    public String getChallenges(Model model) {
        List<ChallengeViewDto> continueChallenges = zeroWasteService.findContinueChallenge();
        List<ChallengeViewDto> endChallenges = zeroWasteService.findEndChallenge();

        model.addAttribute("continueChallenges", continueChallenges);
        model.addAttribute("endChallenges", endChallenges);
        return "/ecovalue/zerowaste/challenge";
    }

    @PostMapping("/challenge")
    public String postChallenge() {
        return "#";
    }
}
