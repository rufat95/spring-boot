package com.rufat.exception_management.exceptions;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1001", "Employee not found."),
    GENERAL_EXCEPTION("9999", "Any case error");

    private final String code;
    private final String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
