package com.liveeasystreet.ecovalue.repository.thumbsup;

import com.liveeasystreet.ecovalue.domain.Board;
import com.liveeasystreet.ecovalue.domain.ThumbsUp;
import com.liveeasystreet.ecovalue.dto.board.ThumbsUpDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Mapper
public interface BoardThumbsUpMapper {

    // 추천
    void thumbsUp(ThumbsUpDto thumbsUpDto);

    // 추천 누른적 있는지 조회
    Optional<ThumbsUp> isThumbUpBefore(ThumbsUpDto thumbsUpDto);

    // 추천 여부
    Optional<ThumbsUp> isThumbUp(ThumbsUpDto thumbsUpDto);

    // 게시판 추천 개수
    int thumbCount(Long boardId);

    // 추천 업데이트
    int thumbsUpFetch(ThumbsUpDto thumbsUpDto);

    // 추천 취소
    void deleteThumbsUp(ThumbsUpDto thumbsUpDto);

}
