package com.liveeasystreet.ecovalue.service.image;

import com.liveeasystreet.ecovalue.domain.BoardImage;
import com.liveeasystreet.ecovalue.repository.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageServiceInterface{

    private final ImageRepository imageRepository;
    @Override
    public void save(BoardImage boardImage) {
        imageRepository.save(boardImage);
    }

    @Override
    public void updateBoardIdByStoreFileName(List<String> imageURLs, Long boardId) {
        for(String imageURL : imageURLs){
            try {
                String fileName = imageURL.substring(imageURL.lastIndexOf('/') + 1, imageURL.lastIndexOf('?'));
                imageRepository.updateBoardIdByStoreFileName(fileName,boardId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
