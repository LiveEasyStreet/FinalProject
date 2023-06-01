package com.liveeasystreet.ecovalue.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Board에 저장되는 이미지가 저장되는 형태
 */
@Data
public class BoardImage {

    //게시판 번호
    private Long boardId;

    //이미지 번호
    private Long imageId;

    // 사진 경로
    private UploadImageFile attachFile;

    //저장 시간
    LocalDateTime uploadDate;

    public BoardImage(UploadImageFile attachFile, LocalDateTime uploadDate) {
        this.attachFile = attachFile;
        this.uploadDate = uploadDate;
    }
}
