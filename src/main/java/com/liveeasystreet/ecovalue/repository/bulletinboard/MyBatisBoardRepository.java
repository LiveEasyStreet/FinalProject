package com.liveeasystreet.ecovalue.repository.bulletinboard;

import com.liveeasystreet.ecovalue.cond.board.BoardSearchCond;
import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.dto.board.BoardUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MyBatisBoardRepository implements BoardRepository{

    private final BoardMapper boardMapper;
    @Override
    public void save(Board board) {
        boardMapper.save(board);
    }

    @Override
    public void deleteById(Long boardId) {
        boardMapper.deleteById(boardId);
    }

    @Override
    public void update(Long boardId, BoardUpdateDto boardUpdateDto) {
        boardMapper.update(boardId,boardUpdateDto);
    }

    @Override
    public List<Board> findAll(BoardSearchCond boardSearchCond) {
        return boardMapper.findAll(boardSearchCond);
    }

    @Override
    public Optional<Board> findById(Long boardId) {
        return boardMapper.findById(boardId);
    }

    @Override
    public Long findLength() {
        return boardMapper.findLength();
    }

    @Override
    public Long viewCountUp(Long boardId) {
        return boardMapper.viewCountUp(boardId);
    }
}
