package az.texnoera.texnoera.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResult {
    private Integer statusCode;
    private String message;
}
