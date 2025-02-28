package com.rufat_ecomers.ecomers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    USER_NOT_FOUND(1001, "User not found"),
    ANY_ERRORS(1100, "Any Errors");

    private Integer code;
    private String message;
}
