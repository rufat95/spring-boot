package com.ecommerce.Ecommerce.result.exceptions;

import com.ecommerce.Ecommerce.enums.StatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class Handler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResult> handleExceptions(BaseException b){
        log.error(b.getMessage());

        return ResponseEntity.status(b.getHttpStatus())
                .body(new ErrorResult(b.getStatusCodes().getCode(),
                        b.getStatusCodes().getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResult> handleValidations(MethodArgumentNotValidException m){
        List<String> validations = new ArrayList<>();

        m.getAllErrors().forEach(e -> {
            log.error(e.getDefaultMessage());
            validations.add(e.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult(
                        StatusCodes.ANY_ERROR.getCode(),
                        StatusCodes.ANY_ERROR.getMessage(),
                        validations
                ));
    }
}
