package com.liveeasystreet.ecovalue.service.bulletinboard;

import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.repository.bulletinboard.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements iBoardService{

    private final BoardRepository boardRepository;

    @Override
    public Board BoardViewService(Long boardId) {
        boardRepository.viewCountUp(boardId);
        return boardRepository.findById(boardId).orElse(null);
    }
}
