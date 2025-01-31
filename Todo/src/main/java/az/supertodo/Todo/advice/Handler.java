package az.supertodo.Todo.advice;

import az.supertodo.Todo.Enums.StatusCode;
import az.supertodo.Todo.exception.BaseException;
import az.supertodo.Todo.model.ErrorDataResult;
import az.supertodo.Todo.model.ErrorResult;
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
    public ResponseEntity<ErrorResult> handleBaseException(BaseException e){
        log.error(e.getStatusCode().getMessage());
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResult(e.getStatusCode().getStatusCode(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResult> handleField(MethodArgumentNotValidException e){
        List<String> errors = new ArrayList<>();

        e.getAllErrors().forEach(error -> {
            log.error(error.getDefaultMessage());
            errors.add(error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult(StatusCode.ERROR_FIELDS.getStatusCode(),
                        StatusCode.ERROR_FIELDS.getMessage(), errors));
    }


}
