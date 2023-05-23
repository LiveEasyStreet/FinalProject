package com.liveeasystreet.ecovalue.service.member;

import com.liveeasystreet.ecovalue.domain.Member;
import com.liveeasystreet.ecovalue.dto.board.ThumbsUpDto;
import com.liveeasystreet.ecovalue.repository.member.MemberRepository;
import com.liveeasystreet.ecovalue.repository.thumbsup.ThumbUpRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDataAccessService {
    /**
     * 멤버 데이터 접근을 위한 서비스
     */
    private final MemberRepository memberRepository;

    private final ThumbUpRepository thumbUpRepository;

    public String getMemberNickNameById(Long id){
        Member member = memberRepository.findById(id).orElse(null);
        if (member != null){
            return member.getNickName();
        }else {
            return "(알 수 없는 사용자)";
        }
    }

    public Long getMemberIdByLoginId(String loginId){

        Member member = memberRepository.findByLoginId(loginId).orElse(null);
        if(member!=null){
            return member.getMemberId();
        }
        return 0L;
    }

    /**
     * 좋아요를 누른 게시물인지 확인
     */
    public boolean isBoardThumbsUp(Long boardId, String loginId){

        Member member = memberRepository.findByLoginId(loginId).orElse(null);
        if(member!=null){
            ThumbsUpDto thumbsUpDto = new ThumbsUpDto(boardId,member.getMemberId());
            if(thumbUpRepository.isThumbUp(thumbsUpDto).orElse(null) != null){
                return true;
            }
        }
        return false;
    }
}
