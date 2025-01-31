package az.supertodo.Todo.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    USER_NOT_FOUND(1001, "Error: User not found !"),
    TASK_NOT_FOUND(1002, "Error: Task not found !"),
    ERROR_FIELDS(1100, "Any Error");

    private final int statusCode;
    private final String message;
}
