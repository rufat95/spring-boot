package az.supertodo.Todo.responses;

import az.supertodo.Todo.Enums.TaskEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateResponse {
    private Long id;
    private Long userId;
    private String taskName;
    private String priority;
    private TaskEnum taskEnum;
}
