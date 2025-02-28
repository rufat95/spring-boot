package az.texnoera.texnoera.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorDataResult {
    private Integer statusCode;
    private String message;
    private List<String> validation;
}
