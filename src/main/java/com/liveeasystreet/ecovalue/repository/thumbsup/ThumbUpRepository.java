package com.liveeasystreet.ecovalue.repository.thumbsup;

import com.liveeasystreet.ecovalue.domain.ThumbsUp;
import com.liveeasystreet.ecovalue.dto.board.ThumbsUpDto;

import java.util.Optional;

public interface ThumbUpRepository {
    // 추천
    void thumbsUp(ThumbsUpDto thumbsUpDto);

    Optional<ThumbsUp> thumbsUpBefore(ThumbsUpDto thumbsUpDto);
    int thumbsUpFetch(ThumbsUpDto thumbsUpDto);

    // 추천 취소
    void deleteThumbsUp(ThumbsUpDto thumbsUpDto);

    // 추천 여부
    Optional<ThumbsUp> isThumbUp(ThumbsUpDto thumbsUpDto);

    // 게시판 추천 개수
    int thumbCount(Long boardId);
}
