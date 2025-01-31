package az.supertodo.Todo.requests;

import az.supertodo.Todo.Enums.TaskEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskUpdateRequest {
    @NotNull(message = "Task name is not be null.")
    @NotEmpty(message = "Task name is not be empty.")
    @NotBlank(message = "Task name does not contain blank.")
    @Size(message = "Task name must be minimum 3, maximum 20 character.")
    private String taskName;
    private String priority;
    private TaskEnum taskEnum;
}
