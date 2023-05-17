package com.liveeasystreet.ecovalue.service.bulletinboard;

import com.liveeasystreet.ecovalue.domain.Board;

import java.util.Optional;

public interface iBoardService {

    public Board findBoardById(Long boardId);
}
