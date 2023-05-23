package com.liveeasystreet.ecovalue.controller.upcycle;

import com.liveeasystreet.ecovalue.controller.login.SessionConst;
import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.BoardCategory;
import com.liveeasystreet.ecovalue.dto.board.*;
import com.liveeasystreet.ecovalue.dto.comment.CommentResponseDto;
import com.liveeasystreet.ecovalue.dto.member.MemberSessionDto;
import com.liveeasystreet.ecovalue.service.bulletinboard.iBoardService;
import com.liveeasystreet.ecovalue.service.member.MemberDataAccessService;
import com.liveeasystreet.ecovalue.service.thumbsup.ThumbServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UpcycleController {

    private final iBoardService boardService;

    private final MemberDataAccessService memberDataAccessService;

    private final ThumbServiceImpl thumbService;



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
                                     @PathVariable Long boardId,
                                     HttpServletRequest request){


        Board board=boardService.BoardViewService(boardId);

        HttpSession session = request.getSession(false);
        /**
         * 로그인 되어있으면 좋아요를 누를 수 있게 하기 위해
         */
        boolean isThumbMine = false;
        if (session !=null){
            MemberSessionDto memberSessionDto = (MemberSessionDto) session.getAttribute(SessionConst.MEMBER_LOGIN);
            log.info("갤러리 memberSession : {}",memberSessionDto);
            isThumbMine =memberDataAccessService.isBoardThumbsUp(boardId,memberSessionDto.getLoginId());
        }

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
        model.addAttribute("thumbUpCount",thumbService.thumbCount(boardId));
        model.addAttribute("myThumb",isThumbMine);

        // 테스트 정보
        {
            List<CommentResponseDto> commentResponseDto = new ArrayList<>();
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);
            commentResponseDto.add(new CommentResponseDto(1L,"테스트001","테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 ",formattedDateTime));
            dateTime = LocalDateTime.now();
            formattedDateTime = dateTime.format(formatter);
            commentResponseDto.add(new CommentResponseDto(2L,"테스트최대길이열두글자용","테스트 내용 2",formattedDateTime));
            model.addAttribute("commentList",commentResponseDto);
        }




        return "ecovalue/upcycle/upcycle_gallery_board";
    }


    @ResponseBody
    @PostMapping("/upGallery/boardUp")
    public ThumbsUpResponseDto boardUpPage(@RequestBody ThumbsUpRequestDto thumbsUpRequestDto,
                           @SessionAttribute(SessionConst.MEMBER_LOGIN) MemberSessionDto memberSessionDto) {
        log.info("thumpsUpDto : {} ",thumbsUpRequestDto);

        ThumbsUpResponseDto thumbsUpResponseDto = new ThumbsUpResponseDto();
        boolean thumbAct = false;
        if (memberSessionDto !=null){
            /**
             * Spring _CSRF 에 대해 찾아보기
             */
            //로그인 아이디가 다르다면 문제 발생 가능성 있으므로 반영 x
            if (!thumbsUpRequestDto.getLoginId().equals(memberSessionDto.getLoginId())){
                log.info("memberSession 과 화면 id 다름");
                log.info("memberSession LoginId = {} 타입 = {} 길이 = {}",memberSessionDto.getLoginId(),memberSessionDto.getLoginId().getClass(),memberSessionDto.getLoginId().length());
                log.info("thumbsUpRequestDto LoginId = {} 타입 = {} 길이 = {}",thumbsUpRequestDto.getLoginId(), thumbsUpRequestDto.getLoginId().getClass(), thumbsUpRequestDto.getLoginId().length());
            }
            else {
                Long memberId=memberDataAccessService.getMemberIdByLoginId(memberSessionDto.getLoginId());
                thumbsUpResponseDto.setThumbAct(thumbService.thumbActing(new ThumbsUpDto(thumbsUpRequestDto.getBoardId(),memberId)));
                thumbsUpResponseDto.setProcessCompleted(true);
            }
            log.info("갤러리 memberSession : {}",memberSessionDto);

            thumbsUpResponseDto.setBoardUpCount(thumbService.thumbCount(thumbsUpRequestDto.getBoardId()));
        }

        return thumbsUpResponseDto;
    }

}
