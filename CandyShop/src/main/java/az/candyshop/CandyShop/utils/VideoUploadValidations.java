package az.candyshop.CandyShop.utils;

import az.candyshop.CandyShop.entities.Product;
import az.candyshop.CandyShop.enums.StatusCode;
import az.candyshop.CandyShop.result.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class VideoUploadValidations {

    public void videoValidation(MultipartFile video){
        if(video.getSize() > 100 * 1024 * 1024){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.VIDEO_SIZE);
        }

        String contentType = video.getContentType();
        if(contentType == null){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.VIDEO_NOT_NULL);
        }

        String fileOriginName = video.getOriginalFilename();
        if(fileOriginName == null || !fileOriginName.toLowerCase().endsWith(".mp4")){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.VIDEO_FORMAT);
        }

        if (!contentType.equals("video/mp4")){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.VIDEO_MIME);
        }
    }

    public void productVideoUpload(MultipartFile video, Product product) throws IOException {
        videoValidation(video);
        final String UPLOAD_DIR_PICTURE = "a_products_video/";
        String pictureName = UUID.randomUUID() + "_" + video.getOriginalFilename();
        Path picturePath = Paths.get(UPLOAD_DIR_PICTURE + pictureName);
        Files.copy(video.getInputStream(), picturePath);
        product.setVideo(picturePath.toString());
    }
}
