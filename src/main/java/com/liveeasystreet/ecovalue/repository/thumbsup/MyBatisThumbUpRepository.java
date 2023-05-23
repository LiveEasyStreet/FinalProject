package com.liveeasystreet.ecovalue.repository.thumbsup;

import com.liveeasystreet.ecovalue.domain.ThumbsUp;
import com.liveeasystreet.ecovalue.dto.board.ThumbsUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisThumbUpRepository implements ThumbUpRepository{

    private final BoardThumbsUpMapper boardThumbsUpMapper;
    @Override
    public void thumbsUp(ThumbsUpDto thumbsUpDto) {
        boardThumbsUpMapper.thumbsUp(thumbsUpDto);
    }

    @Override
    public Optional<ThumbsUp> thumbsUpBefore(ThumbsUpDto thumbsUpDto) {
        return boardThumbsUpMapper.isThumbUpBefore(thumbsUpDto);
    }

    @Override
    public int thumbsUpFetch(ThumbsUpDto thumbsUpDto){
        return boardThumbsUpMapper.thumbsUpFetch(thumbsUpDto);
    }

    @Override
    public void deleteThumbsUp(ThumbsUpDto thumbsUpDto) {
        boardThumbsUpMapper.deleteThumbsUp(thumbsUpDto);
    }

    @Override
    public Optional<ThumbsUp> isThumbUp(ThumbsUpDto thumbsUpDto) {
        return boardThumbsUpMapper.isThumbUp(thumbsUpDto);
    }

    @Override
    public int thumbCount(Long boardId) {
        return boardThumbsUpMapper.thumbCount(boardId);
    }

}
