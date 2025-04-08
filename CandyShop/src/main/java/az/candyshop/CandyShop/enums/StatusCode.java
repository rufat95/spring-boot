package az.candyshop.CandyShop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    USER_NOT_FOUND(1001, "User not found !"),
    USER_UNIQUE(1002, "This user is already exist, " +
            "please log in or click forget password button. "),
    USER_LOG_IN(1003, "You can not purchase, please sing up or sign in first"),
    USER_SIGN_IN(1004, "Email or password incorrect"),

    IMAGE_NOT_NULL(1007, "Picture can not be null !"),
    IMAGE_SIZE(1008, "Picture size must be maximum 100 MB !"),
    IMAGE_FORMAT(1009, "Only jpg, jpeg and png Picture are allowed !"),
    IMAGE_MIME(1010, "Picture mime format can be " +
            "only jpg, jpeg and png formats !"),

    VIDEO_NOT_NULL(1011, "Video can not be null !"),
    VIDEO_SIZE(1012, "Video size must be maximum 100 MB !"),
    VIDEO_FORMAT(1013, "Only mp4 videos are allowed !"),
    VIDEO_MIME(1014, "Video mime format can be only mp4 formats !"),

    PRODUCT_NOT_FOUND(1100, "Product not found !"),
    PRODUCT_UNIQUE(1101, "This product is already exist. " +
            "You only add product"),

    ORDER_NOT_FOUND(1102, "Order not found !"),
    ORDER_ITEMS_NOT_FOUND(1103, "Order items not found"),
    ORDER_OFF(1104, "This order is already in your possession " +
            "and cannot be canceled. Please contact customer service."),

    ANY_ERROR(9999, "Any Errors...");

    private final Integer code;
    private final String message;
}
