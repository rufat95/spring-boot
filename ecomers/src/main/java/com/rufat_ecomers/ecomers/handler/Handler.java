package com.rufat_ecomers.ecomers.handler;

import com.rufat_ecomers.ecomers.enums.StatusCode;
import com.rufat_ecomers.ecomers.exceptions.BaseException;
import com.rufat_ecomers.ecomers.model.ErrorDataResult;
import com.rufat_ecomers.ecomers.model.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class Handler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResult> handleErrors(BaseException b){
        log.error(b.getMessage());

        return ResponseEntity.status(b.getHttpStatus())
                .body(new ErrorResult(b.getStatusCode().getCode(), b.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResult> handleValidations(MethodArgumentNotValidException m){
        List<String> validations = new ArrayList<>();

        m.getAllErrors().forEach(error -> {
            log.error(error.getDefaultMessage());
            validations.add(error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult(
                        StatusCode.ANY_ERRORS.getCode(),
                        StatusCode.ANY_ERRORS.getMessage(),
                        validations));
    }
}
