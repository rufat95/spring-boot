package com.file.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    FILE_NOT_NULL(1007, "File can not be null !"),
    FILE_SIZE(1008, "File size must be maximum 10 MB !"),
    FILE_FORMAT(1009, "Only jpg and png files are allowed !"),
    FILE_MIME(1010, "File mime format can be only jpg and png formats !"),
    ANY_ERROR(1100, "Any Error");

    private final Integer code;
    private final String message;
}
