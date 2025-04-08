package az.candyshop.CandyShop.utils;

import az.candyshop.CandyShop.entities.Product;
import az.candyshop.CandyShop.entities.User;
import az.candyshop.CandyShop.enums.StatusCode;
import az.candyshop.CandyShop.result.exception.BaseException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ImageUploadValidations {
    private void imageValidation(MultipartFile image){
        if(image.getSize() > 100 * 1024 * 1024){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.IMAGE_SIZE);
        }

        String contentType = image.getContentType();
        if(contentType == null){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.IMAGE_NOT_NULL);
        }

        String fileOriginName = image.getOriginalFilename();
        if(fileOriginName == null || (!fileOriginName.toLowerCase().endsWith(".jpg") &&
                !fileOriginName.toLowerCase().endsWith(".jpeg") &&
                    !fileOriginName.toLowerCase().endsWith("png"))
        ){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.IMAGE_FORMAT);
        }

        if (!contentType.equals("image/jpeg") &&
                !contentType.equals("image/jpg") &&
                !contentType.equals("image/png")){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.IMAGE_MIME);
        }
    }

    public void productImageUpload(MultipartFile picture, Product product) throws IOException {
        imageValidation(picture);
        final String UPLOAD_DIR_PICTURE = "a_products_image/";
        String pictureName = UUID.randomUUID() + "_" + picture.getOriginalFilename();
        Path picturePath = Paths.get(UPLOAD_DIR_PICTURE + pictureName);
        Files.copy(picture.getInputStream(), picturePath);
        product.setPicture(picturePath.toString());
    }

    public void userImageUpload(MultipartFile userImage, User user) throws IOException {
        imageValidation(userImage);
        final String UPLOAD_DIR_PICTURE = "a_users_image/";
        String pictureName = UUID.randomUUID() + "_" + userImage.getOriginalFilename();
        Path picturePath = Paths.get(UPLOAD_DIR_PICTURE + pictureName);
        Files.copy(userImage.getInputStream(), picturePath);
        user.setUserImage(picturePath.toString());
    }
}
