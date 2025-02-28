package com.file.file.utils;

import com.file.file.StatusCode;
import com.file.file.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadValidations {
    public void userFile(MultipartFile profilePicture) {
        if (profilePicture.getSize() > 10 * 1024 * 1024) { // 10 MB
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.FILE_SIZE);
        }
        String contentType = profilePicture.getContentType();
        if (contentType == null){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.FILE_NOT_NULL);
        }

        String fileOriginName = profilePicture.getOriginalFilename();
        if (fileOriginName == null ||
                (!fileOriginName.toLowerCase().endsWith(".jpg") &&
                        !fileOriginName.toLowerCase().endsWith(".png"))) {
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.FILE_FORMAT);
        }

        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.FILE_MIME);
        }
    }
}
