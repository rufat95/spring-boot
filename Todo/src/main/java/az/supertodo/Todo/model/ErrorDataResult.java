package az.supertodo.Todo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
public class ErrorDataResult {
    private Integer statusCode;
    private String message;
    private List<String> data;

    public ErrorDataResult(Integer statusCode, String message, List<String> data){
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
