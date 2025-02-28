package com.ecommerce.Ecommerce.result.exceptions;

import com.ecommerce.Ecommerce.enums.StatusCodes;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final StatusCodes statusCodes;

    public BaseException(HttpStatus httpStatus, StatusCodes statusCodes) {
        super(statusCodes.getMessage());
        this.httpStatus = httpStatus;
        this.statusCodes = statusCodes;
    }
}
