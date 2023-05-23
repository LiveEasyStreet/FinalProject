package com.liveeasystreet.ecovalue.service.thumbsup;

import com.liveeasystreet.ecovalue.domain.ThumbsUp;
import com.liveeasystreet.ecovalue.dto.board.ThumbsUpDto;
import com.liveeasystreet.ecovalue.repository.thumbsup.ThumbUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThumbServiceImpl implements iThumbService{

    private final ThumbUpRepository thumbUpRepository;

    @Override
    public boolean thumbActing(ThumbsUpDto thumbsUpDto) {
        // 이미 추천되어 있는지 여부 확인
        ThumbsUp thumbsUp = thumbUpRepository.thumbsUpBefore(thumbsUpDto).orElse(null);

        /**
         * thumbsUp이 null 이면 아직 추천을 누른적이 없음
         * isDeleted 가 true 일 경우 삭제되었다는 것 -> 즉 추천을 누르지 않았다는것
         * isDeleted 가 false 라면 추천을 누른 상태라는것
         */
        if (thumbsUp ==null){
            // thumbsUp이 없으면 추천 누른적이 없으므로
            thumbUpRepository.thumbsUp(thumbsUpDto);

            return true;
        }
        else if (thumbsUp.isDeleted()) {
            // thumbsUp 은 있지만 삭제되었다면 업데이트
            thumbUpRepository.thumbsUpFetch(thumbsUpDto);

            return true;
        } else {
            thumbUpRepository.deleteThumbsUp(thumbsUpDto);

            return false;
        }

    }

    public int thumbCount(Long boardId){
        return thumbUpRepository.thumbCount(boardId);
    }
}
