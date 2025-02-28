package az.texnoera.texnoera.exceptions;

import az.texnoera.texnoera.enums.StatusCode;
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
    public ResponseEntity<ErrorResult> exceptionHandler(BaseException b){
        log.error(b.getMessage());
        return ResponseEntity.status(b.getHttpStatus())
                .body(new ErrorResult(b.getStatusCode().getCode(),
                            b.getStatusCode().getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResult> handleValidation(MethodArgumentNotValidException m){
        List<String> validations = new ArrayList<>();

        m.getAllErrors().forEach(e -> {
            log.error(e.getDefaultMessage());
            validations.add(e.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult(
                        StatusCode.ANY_ERROR.getCode(),
                        StatusCode.ANY_ERROR.getMessage(),
                        validations
                ));
    }
}
