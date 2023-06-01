package com.liveeasystreet.ecovalue.service.bulletinboard;

import com.liveeasystreet.ecovalue.cond.board.BoardSearchCond;
import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.BoardCategory;
import com.liveeasystreet.ecovalue.domain.Comment;
import com.liveeasystreet.ecovalue.dto.board.BoardWriteDto;
import com.liveeasystreet.ecovalue.dto.comment.CommentGetDto;
import com.liveeasystreet.ecovalue.repository.bulletinboard.BoardRepository;
import com.liveeasystreet.ecovalue.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements iBoardService{

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    @Override
    public void save(BoardWriteDto board) {
        boardRepository.save(board);
    }

    @Override
    public Board BoardViewService(Long boardId) {
        boardRepository.viewCountUp(boardId);
        return boardRepository.findById(boardId).orElse(null);
    }

    @Override
    public List<Board> upGalleryBoardSearch(int pageSize, int offset, BoardCategory boardCategory, String search_target, String search_keyword) {
        BoardSearchCond boardSearchCond = setBoardSearchCond(search_target, search_keyword, boardCategory);
        return boardRepository.findAllWithPaging(pageSize,offset,boardSearchCond);
    }
    @Override
    public int upGalleryBoardSearchPageCount(int pageSize, BoardCategory boardCategory, String search_target, String search_keyword) {
        BoardSearchCond boardSearchCond = setBoardSearchCond(search_target, search_keyword, boardCategory);
        return boardRepository.pageCount(pageSize,boardSearchCond);
    }



    @Override
    public List<Long> boardPageNum(List<Board> boardLists) {
        List<Long> result = new ArrayList<>();
        if (boardLists != null){
            for(Board board : boardLists){
                result.add(board.getBoardId());
            }
        }
        return result;
    }

    @Override
    public List<Board> findAll(BoardSearchCond boardSearchCond) {
        return boardRepository.findAll(boardSearchCond);
    }

    @Override
    public List<Comment> findByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }

    @Override
    public void insertComment(Comment comment) {
        commentRepository.insertComment(comment);
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    private static BoardSearchCond setBoardSearchCond(String search_target, String search_keyword, BoardCategory boardCategory) {
        BoardSearchCond boardSearchCond = new BoardSearchCond(boardCategory);
        if ("title_content".equals(search_target)){
            boardSearchCond.setTitle(search_keyword);
            boardSearchCond.setContents(search_keyword);
        } else if ("title".equals(search_target)) {
            boardSearchCond.setTitle(search_keyword);
        }
        else if ("content".equals(search_target)){
            boardSearchCond.setContents(search_keyword);
        } else if ("comment".equals(search_target)) {
            ;;
        }
        else if ("nick_name".equals(search_target)){
            boardSearchCond.setNickName(search_keyword);
        }

        return boardSearchCond;
    }
}
