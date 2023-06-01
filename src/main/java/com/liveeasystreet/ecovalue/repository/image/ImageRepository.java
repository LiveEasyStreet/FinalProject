package com.liveeasystreet.ecovalue.repository.image;

import com.liveeasystreet.ecovalue.domain.BoardImage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    // 이미지 저장
    void save(BoardImage boardImage);

    // 이미지 삭제
    void deleteById(Long imageId);

    // 이미지 삭제 - 게시판 삭제에 따른 진행
    void deleteByBoardId(Long boardId);

    // 이미지 삭제 - 이미지 날짜, 이름에 따른 삭제
    void deleteByNameAndDate(String storeFileName, LocalDateTime dateTime);

    // 이미지 조회 - 게시물 하나에 들어간 모든 사진 조회
    List<BoardImage> findByBoardId(Long boardId);

    // 이미지 조회 - 해당 게시물의 첫번째 사진을 가져오는 것
    Optional<BoardImage> findOneByBoardId(Long boardId);

    void updateBoardIdByStoreFileName(String storeFileName, Long boardId);
}
