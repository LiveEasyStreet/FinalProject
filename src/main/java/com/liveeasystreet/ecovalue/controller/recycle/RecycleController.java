package com.liveeasystreet.ecovalue.controller.recycle;

import com.liveeasystreet.ecovalue.controller.login.SessionConst;
import com.liveeasystreet.ecovalue.controller.upcycle.BoardConst;
import com.liveeasystreet.ecovalue.domain.*;
import com.liveeasystreet.ecovalue.dto.board.*;
import com.liveeasystreet.ecovalue.dto.comment.CommentResponseDto;
import com.liveeasystreet.ecovalue.dto.member.MemberSessionDto;
import com.liveeasystreet.ecovalue.file.FileStore;
import com.liveeasystreet.ecovalue.service.bulletinboard.iBoardService;
import com.liveeasystreet.ecovalue.service.image.ImageServiceInterface;
import com.liveeasystreet.ecovalue.service.member.MemberDataAccessService;
import com.liveeasystreet.ecovalue.service.thumbsup.iThumbService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/recycle")
public class RecycleController {

    private final iBoardService boardService;

    private final MemberDataAccessService memberDataAccessService;

    private final iThumbService thumbService;

    private final FileStore fileStore;

    private final ImageServiceInterface imageServiceInterface;

    @GetMapping("/info")
    public String recycle_info(){
        return "ecovalue/recycle/recycle-info";
    }

    /**
     * 리사이클 게시판 화면
     */
    @GetMapping({"/knowHow","/knowHow/"})
    public String reCycle_knowHow(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(value = "search_keyword", required = false) String searchKeyword,
                                  @RequestParam(value = "search_target", defaultValue = "title_content") String searchTarget,
                                  RedirectAttributes redirectAttributes){
        /**
         * 페이지 정보에 대한 부분 시작
         * 페이지 정보의 경우 나중에 합쳐서 보내거나 할 필요 있음
         */
        //현재 페이지
        model.addAttribute("page",page);

        //마지막 페이지와 시작 페이지에 대한 정보
        int lastPage = boardService.upGalleryBoardSearchPageCount(BoardConst.pageSize, BoardCategory.RECYCLE,searchTarget,searchKeyword)+1;
        if(lastPage<page){
            redirectAttributes.addAttribute("page", lastPage);
            return "redirect:/knowHow";
        }
        int startPage = (page/10)*10+1;
        if (page%10==0){
            startPage-=10;
        }
        /**
         * 페이지 정보 부분 종료
         */

        log.info("upGalleryBoardSearchPageCount : {}",boardService.upGalleryBoardSearchPageCount(BoardConst.pageSize,BoardCategory.RECYCLE,searchTarget,searchKeyword));
        log.info("search : {}, {}",searchKeyword,searchTarget);

        // 해당 페이지에서 나올 데이터(게시판)을 포함하는것
        List<Board> boardLists = boardService.upGalleryBoardSearch(BoardConst.pageSize,BoardConst.pageSize*(page-1),BoardCategory.RECYCLE,searchTarget,searchKeyword);
        for (Board boardlist: boardLists) {
            log.info("board stat{}",boardlist);
        }
        List<Long> boardNum = boardService.boardPageNum(boardLists);
        /**
         * 이 아래 부분은 임시 부분으로 원래는 게시판 중 사진이 있는 애들을 대상으로 출력해야되지만 다 완성되지 않아서 List 전체 보냄
         */

        model.addAttribute("boardLists",boardLists);

        /**
         * 임시 끝
         */

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

        return "ecovalue/recycle/recycle-knowHow";
    }

    /**
     * 게시판 작성 페이지
     */
    @GetMapping("/knowHow/write")
    public String reCycle_board_write(Model model){
        model.addAttribute("category",BoardCategory.RECYCLE.getDescription());
        model.addAttribute("tags", RecycleCategory.getNonEndCategories());
        return "ecovalue/recycle/recycle_knowHow_write";
    }

    /**
     * 게시판 작성 완료후 처리 부분 ->
     * 게시판을 작성해서 올릴 경우 페이지가 저장된 후 부여된 페이지 번호를 리턴
     * 이번호가 정상일 경우 그 페이지로 이동할 수 있게 함
     */
    @ResponseBody
    @PostMapping("/knowHow/write")
    public Long submit_board_write(@RequestBody BoardWriteDto board,
                                   @SessionAttribute(SessionConst.MEMBER_LOGIN) MemberSessionDto memberSessionDto,
                                   RedirectAttributes redirectAttributes){
        // 날짜 설정
        board.setUploadDate(LocalDateTime.now());
        // 리사이클 갤러리 설정
        board.setBoardCategory(1L);
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

    /**
     * 이미지를 올리는 부분
     * 이미지가 추가된후 이미지에 대한 데이터가 서버에 전달,
     * 해당 이미지를 저장 후 이미지 경로를 화면에 보냄
     */
    @ResponseBody
    @PostMapping("/write/image")
    public BoardImage image_submit_test(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("file : {}",file);
        LocalDateTime date = LocalDateTime.now();

        UploadImageFile attachFile = fileStore.storeFile(file, date);

        BoardImage boardImage = new BoardImage(attachFile,date);
        imageServiceInterface.save(boardImage);

        return boardImage;
    }

    /**
     * 이미지 가져오는 부분
     * 이미지 태그내에서 해당 이미지에 접근해서 이미지를 가져가는 부분
     */
    @ResponseBody
    @GetMapping("/write/image/{filename}")
    public UrlResource downloadImage(@PathVariable String filename,
                                     @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) throws MalformedURLException {

        return new UrlResource("file:"+fileStore.getFullPath(filename,dateTime));
    }

    /**
     * 게시판 조회 화면
     * 게시판 번호로 들어오면 해당 게시물 보여주는 부분
     */
    @GetMapping("/views/{boardId}")
    public String reCycle_board_view(Model model,
                                     @PathVariable Long boardId,
                                     HttpServletRequest request,
                                     @SessionAttribute(value = SessionConst.MEMBER_LOGIN, required = false) MemberSessionDto memberSessionDto){


        Board board=boardService.BoardViewService(boardId);

        /**
         * 로그인 되어있으면 좋아요를 누를 수 있게 하기 위해
         */
        boolean isThumbMine = false;
        if (memberSessionDto !=null){
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


        return "ecovalue/recycle/reCycle_knowHow_board";
    }

    /**
     * 게시판에서 댓글 작성할때 post 되는 부분
     */
    @ResponseBody
    @PostMapping("/views/{boardId}")
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

    /**
     * 게시판 좋아요 누르는 부분
     */
    @ResponseBody
    @PostMapping("/knowHow/boardUp")
    public ThumbsUpResponseDto boardUpPage(@RequestBody ThumbsUpRequestDto thumbsUpRequestDto,
                                           @SessionAttribute(SessionConst.MEMBER_LOGIN) MemberSessionDto memberSessionDto) {
        log.info("thumpsUpDto : {} ",thumbsUpRequestDto);

        ThumbsUpResponseDto thumbsUpResponseDto = new ThumbsUpResponseDto();
        if (memberSessionDto !=null){
            /**
             * Spring _CSRF 에 대해 찾아보기
             */
            //로그인 아이디가 다르다면 문제 발생 가능성 있으므로 반영 x
            if (!thumbsUpRequestDto.getNickName().equals(memberSessionDto.getNickName())){
                log.info("memberSession 과 화면 id 다름");
                log.info("memberSession LoginId = {} 타입 = {} 길이 = {}",memberSessionDto.getLoginId(),memberSessionDto.getLoginId().getClass(),memberSessionDto.getLoginId().length());
                log.info("thumbsUpRequestDto LoginId = {} 타입 = {} 길이 = {}",thumbsUpRequestDto.getNickName(), thumbsUpRequestDto.getNickName().getClass(), thumbsUpRequestDto.getNickName().length());
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
