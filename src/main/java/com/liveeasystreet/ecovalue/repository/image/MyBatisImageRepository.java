package com.liveeasystreet.ecovalue.repository.image;

import com.liveeasystreet.ecovalue.domain.BoardImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisImageRepository implements ImageRepository{

    private final ImageMapper imageMapper;

    @Override
    public void save(BoardImage boardImage) {
        imageMapper.save(boardImage);
    }

    @Override
    public void deleteById(Long imageId) {
        imageMapper.deleteById(imageId);
    }

    @Override
    public void deleteByBoardId(Long boardId) {
        imageMapper.deleteByBoardId(boardId);
    }

    @Override
    public void deleteByNameAndDate(String storeFileName, LocalDateTime dateTime) {
        imageMapper.deleteByNameAndDate(storeFileName,dateTime);
    }

    @Override
    public List<BoardImage> findByBoardId(Long boardId) {
        return imageMapper.findByBoardId(boardId);
    }

    @Override
    public Optional<BoardImage> findOneByBoardId(Long boardId) {
        return imageMapper.findOneByBoardId(boardId);
    }

    @Override
    public void updateBoardIdByStoreFileName(String storeFileName, Long boardId) {
        imageMapper.updateBoardIdByStoreFileName(storeFileName,boardId);
    }
}
