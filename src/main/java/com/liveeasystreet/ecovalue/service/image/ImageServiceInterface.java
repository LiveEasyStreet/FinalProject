package com.liveeasystreet.ecovalue.service.image;

import com.liveeasystreet.ecovalue.domain.BoardImage;

import java.util.List;

public interface ImageServiceInterface {

    void save(BoardImage boardImage);

    void updateBoardIdByStoreFileName(List<String> imageURLs, Long boardId);

}
