package com.liveeasystreet.ecovalue.domain;

import lombok.Data;

@Data
public class UploadImageFile {

    //고객이 업로드한 파일명
    private String uploadFileName;
    //실제 저장될때 쓰이는 파일명
    private String storeFileName;

    public UploadImageFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
