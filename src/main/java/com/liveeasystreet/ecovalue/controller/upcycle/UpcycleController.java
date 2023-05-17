package com.liveeasystreet.ecovalue.controller.upcycle;

import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.BoardCategory;
import com.liveeasystreet.ecovalue.dto.board.BoardUpdateDto;
import com.liveeasystreet.ecovalue.dto.board.BoardViewDto;
import com.liveeasystreet.ecovalue.service.bulletinboard.iBoardService;
import com.liveeasystreet.ecovalue.service.member.MemberDataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UpcycleController {

    private final iBoardService boardService;

    private final MemberDataAccessService memberDataAccessService;


    @GetMapping("/upcycleInfo")
    public String upcycle_info(){
        return"ecovalue/upcycle/upcycle_info";
    }

    @GetMapping("/upGallery")
    public String upCycle_gallery(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 RedirectAttributes redirectAttributes){
        model.addAttribute("page",page);
        int lastPage = 55;
        if(lastPage<page){
            redirectAttributes.addAttribute("page", lastPage);
            return "redirect:/upGallery";
        }
        int startPage = (page/10)*10+1;
        if (page%10==0){
            startPage-=10;
        }
        model.addAttribute("page",page);
        model.addAttribute("startPage",startPage);
        model.addAttribute("lastPage", lastPage);
        log.info("page = {}, startPage = {}, lastPage = {}",page,startPage,lastPage);

        return "ecovalue/upcycle/upcycle_gallery";
    }

    @GetMapping("/upGallery/views/{boardId}")
    public String upCycle_board_view(Model model,
                                     @PathVariable Long boardId){
        Board board=boardService.findBoardById(boardId);


        BoardViewDto boardViewDto = new BoardViewDto(board);
        boardViewDto.setNickName(memberDataAccessService.getMemberNickNameById(board.getMemberId()));

//        boardViewDto.setTitle("테스트 제목 1");
//        boardViewDto.setBoardCategory(BoardCategory.UP_CYCLE);
//        String testContents = "이것은 테스트 데이터 입니다. \n텍스트 줄바꿈 테스트 입니다. \n줄바꿈이 제대로 되는지 보는 용도";
//        testContents=testContents.replaceAll("\n","<br/>");
//        boardViewDto.setContents(testContents);
//        boardViewDto.setNickName("test테001");
//        LocalDate localDate= LocalDate.now();
//        boardViewDto.setUploadDate(localDate);
//        boardViewDto.setViews(24);

        model.addAttribute(boardViewDto);

        return "ecovalue/upcycle/upcycle_gallery_board";
    }

}
