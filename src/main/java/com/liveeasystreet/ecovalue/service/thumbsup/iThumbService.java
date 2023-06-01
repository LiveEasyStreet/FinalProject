package com.liveeasystreet.ecovalue.service.thumbsup;

import com.liveeasystreet.ecovalue.dto.board.ThumbsUpDto;
import com.liveeasystreet.ecovalue.dto.board.ThumbsUpRequestDto;

public interface iThumbService {
    /**
     * 추천 여부 확인후 추천을 하거나 취소하는 로직
     * 리턴값으로 추천을 했다면 true
     */
    boolean thumbActing(ThumbsUpDto thumbsUpDto);

    public int thumbCount(Long boardId);
}
