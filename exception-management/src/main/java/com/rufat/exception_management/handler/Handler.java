package com.rufat.exception_management.handler;

import com.rufat.exception_management.exceptions.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> handlerBaseException(BaseException baseException){
        return ResponseEntity.badRequest().body(baseException.getMessage());
    }
}
