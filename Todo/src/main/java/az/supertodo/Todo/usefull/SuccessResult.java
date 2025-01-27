package az.supertodo.Todo.usefull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResult<T> {
    private T user;
    private String message;
}
