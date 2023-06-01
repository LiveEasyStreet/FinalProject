package com.liveeasystreet.ecovalue.controller.upcycle;

import com.liveeasystreet.ecovalue.cond.board.BoardSearchCond;
import com.liveeasystreet.ecovalue.controller.login.SessionConst;
import com.liveeasystreet.ecovalue.domain.*;
import com.liveeasystreet.ecovalue.dto.board.*;
import com.liveeasystreet.ecovalue.dto.comment.CommentGetDto;
import com.liveeasystreet.ecovalue.dto.comment.CommentResponseDto;
import com.liveeasystreet.ecovalue.dto.member.MemberSessionDto;
import com.liveeasystreet.ecovalue.file.FileStore;
import com.liveeasystreet.ecovalue.service.bulletinboard.iBoardService;
import com.liveeasystreet.ecovalue.service.image.ImageServiceInterface;
import com.liveeasystreet.ecovalue.service.member.MemberDataAccessService;
import com.liveeasystreet.ecovalue.service.thumbsup.ThumbServiceImpl;
import com.liveeasystreet.ecovalue.service.thumbsup.iThumbService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UpcycleController {

    private final iBoardService boardService;

    private final MemberDataAccessService memberDataAccessService;

    private final iThumbService thumbService;

    private final FileStore fileStore;

    private final ImageServiceInterface imageServiceInterface;


    @GetMapping({"/upcycleInfo","/upcycleInfo/"})
    public String upcycle_info(){
        return"ecovalue/upcycle/upcycle_info";
    }

    @GetMapping({"/upGallery/","/upGallery"})
    public String upCycle_gallery(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(value = "search_keyword", required = false) String searchKeyword,
                                  @RequestParam(value = "search_target", defaultValue = "title_content") String searchTarget,
                                  RedirectAttributes redirectAttributes){
        model.addAttribute("page",page);
        int lastPage = boardService.upGalleryBoardSearchPageCount(BoardConst.pageSize,BoardCategory.UP_CYCLE,searchTarget,searchKeyword)+1;
        if(lastPage<page){
            redirectAttributes.addAttribute("page", lastPage);
            return "redirect:/upGallery";
        }
        int startPage = (page/10)*10+1;
        if (page%10==0){
            startPage-=10;
        }
        log.info("upGalleryBoardSearchPageCount : {}",boardService.upGalleryBoardSearchPageCount(BoardConst.pageSize,BoardCategory.UP_CYCLE,searchTarget,searchKeyword));
        log.info("search : {}, {}",searchKeyword,searchTarget);

        List<Board> boardLists = boardService.upGalleryBoardSearch(BoardConst.pageSize,BoardConst.pageSize*(page-1),BoardCategory.UP_CYCLE,searchTarget,searchKeyword);
        List<Long> boardNum = boardService.boardPageNum(boardLists);

        //현재 페이지에 이어줄 보드 번호
        model.addAttribute("boardNum",boardNum);
        log.info("boardNum : {}",boardNum);

        // 검색어 페이징 이후 유지 위한 값
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("searchTarget", searchTarget);

        //페이징 위한 데이터
        model.addAttribute("page",page);
        model.addAttribute("startPage",startPage);
        model.addAttribute("lastPage", lastPage);
        log.info("page = {}, startPage = {}, lastPage = {}",page,startPage,lastPage);

        return "ecovalue/upcycle/upcycle_gallery";
    }
    @GetMapping("/upGallery/write")
    public String upCycle_board_write(Model model){
        model.addAttribute("category",BoardCategory.UP_CYCLE.getDescription());
        model.addAttribute("tags",UpCycleCategory.getNonEndCategories());
        return "ecovalue/upcycle/upcycle_gallery_board_write";
    }

    @ResponseBody
    @PostMapping("/upGallery/write")
    public Long submit_board_write(@RequestBody BoardWriteDto board,
                                           @SessionAttribute(SessionConst.MEMBER_LOGIN) MemberSessionDto memberSessionDto,
                                           RedirectAttributes redirectAttributes){
        // 날짜 설정
        board.setUploadDate(LocalDateTime.now());
        // 업사이클 갤러리 설정
        board.setBoardCategory(2L);
        board.setLoginId(memberSessionDto.getLoginId());
        board.setViews(0);
        boardService.save(board);
        //
        String decodeText = board.getContents();
        decodeText = URLDecoder.decode(decodeText, StandardCharsets.UTF_8);


        log.info("\nencode text : {}",board.getContents());
        log.info("\nhtml text : {}",decodeText);
        log.info("\nboardWriteDto : {}",board);


        /**
         * 나중에 서비스로 옮길 부분 img 태그안의 src 부분만 가져와서 사용
         */
        String patternString = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(decodeText);
        List<String> imageURLs = new ArrayList<>();
        /**
         * /upGallery/write/image/e8c7953d-28e9-4b70-b133-8d12b4874657.png?date=2023-05-31T17:10:43.5427442
         */

        while (matcher.find()){
            String imageUrl = matcher.group(1);
            imageURLs.add(imageUrl);
        }
        imageServiceInterface.updateBoardIdByStoreFileName(imageURLs,board.getBoardId());

        return board.getBoardId();
    }
    @ResponseBody
    @PostMapping("/upGallery/write/image")
    public BoardImage image_submit_test(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("file : {}",file);
        LocalDateTime date = LocalDateTime.now();

        UploadImageFile attachFile = fileStore.storeFile(file, date);

        BoardImage boardImage = new BoardImage(attachFile,date);
        imageServiceInterface.save(boardImage);

        return boardImage;
    }

    @ResponseBody
    @GetMapping("/upGallery/write/image/{filename}")
    public UrlResource downloadImage(@PathVariable String filename,
                                     @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) throws MalformedURLException {

        return new UrlResource("file:"+fileStore.getFullPath(filename,dateTime));
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


        model.addAttribute(boardViewDto);
        model.addAttribute("thumbUpCount",thumbService.thumbCount(boardId));
        model.addAttribute("myThumb",isThumbMine);


        List<Comment> Comments = boardService.findByBoardId(boardId);
        List<CommentResponseDto> commentResponseDto = new ArrayList<>();
        for(Comment comment : Comments){
            commentResponseDto.add(new CommentResponseDto(comment));
        }
        model.addAttribute("commentList",commentResponseDto);


        return "ecovalue/upcycle/upcycle_gallery_board";
    }

    @ResponseBody
    @PostMapping("/upGallery/views/{boardId}")
    public CommentResponseDto commentWrite(Model model,
                               @PathVariable Long boardId,
                               @RequestParam String comment_content,
                               @SessionAttribute(SessionConst.MEMBER_LOGIN) MemberSessionDto memberSessionDto){
        log.info("comment : {}",comment_content);
        Comment commentSample = new Comment();
        commentSample.setBoardId(boardId);
        commentSample.setContents(comment_content);
        commentSample.setNickName(memberSessionDto.getNickName());
        boardService.insertComment(commentSample);
        log.info("input comment : {}",commentSample);
        List<Comment> commentList = boardService.findByBoardId(boardId);
        for(Comment comment : commentList){
            log.info("comment : {}",comment);
        }

        Comment newComment = boardService.findById(commentSample.getCommentId()).orElse(null);
        CommentResponseDto commentResponseDto = new  CommentResponseDto(newComment);
        return commentResponseDto;
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
