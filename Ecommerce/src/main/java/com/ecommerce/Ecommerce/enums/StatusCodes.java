package com.ecommerce.Ecommerce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodes {
    USER_NOT_FOUND(1001, "User not found!"),
    USER_NOT_LOGIN(1002, "Email or password incorrect"),
    USER_UNIQUE(1003, "This user is exist. " +
            "Please log in or click forget password button."),
    FILE_NOT_NULL(1007, "File can not be null !"),
    FILE_SIZE(1008, "File size must be maximum 100 MB !"),
    FILE_FORMAT(1009, "Only jpg and png files are allowed !"),
    FILE_MIME(1010, "File mime format can be only jpg and png formats !"),
    ANY_ERROR(1100, "Any error !");

    private final Integer code;
    private final String message;
}
