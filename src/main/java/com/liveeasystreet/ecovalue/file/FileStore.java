package com.liveeasystreet.ecovalue.file;

import com.liveeasystreet.ecovalue.domain.UploadImageFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName,LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-");
        String formatDate = dateTime.format(formatter);

        String datePath = formatDate.replace("-", File.separator);

        File uploadPath = new File(fileDir, datePath);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        return fileDir+datePath+fileName;
    }


    /**
     * multipart File 받은뒤에 UploadFile 형태로 변경해주는 역할
     */
    public UploadImageFile storeFile(MultipartFile multipartFile, LocalDateTime date) throws IOException {
        if (multipartFile==null ||multipartFile.isEmpty()){
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        //image.png

        String storeFileName = createStoreFileName(originalFileName);

        /**
         * 파일을 해당 위치에 생성
         */
        multipartFile.transferTo(new File(getFullPath(storeFileName,date)));

        return new UploadImageFile(originalFileName,storeFileName);
    }
    public List<UploadImageFile> storefiles(List<MultipartFile> multipartFiles, LocalDateTime date) throws IOException {
        List<UploadImageFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                UploadImageFile uploadFile = storeFile(multipartFile,date);
                storeFileResult.add(uploadFile);
            }
        }
        return storeFileResult;
    }

    private String createStoreFileName(String originalFileName) {
        //서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString();
        /**
         * 파일 명에서 확장자는 가져와야 어떤 파일인지 알려줄 수 있으니 그 작업 수행
         * ex) img.png -> png 얻음
         */
        String ext = extractExt(originalFileName);

        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos+1);
    }

}
