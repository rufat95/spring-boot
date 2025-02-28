package com.rufat_ecomers.ecomers.exceptions;

import com.rufat_ecomers.ecomers.enums.StatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final StatusCode statusCode;

    public BaseException(HttpStatus httpStatus, StatusCode statusCode){
        super(statusCode.getMessage());
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }
}
