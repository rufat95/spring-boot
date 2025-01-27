package az.supertodo.Todo.mappers;

import az.supertodo.Todo.entities.Task;
import az.supertodo.Todo.requests.TaskCreateRequest;
import az.supertodo.Todo.responses.TaskCreateResponse;

public interface TaskMapper {

    static Task createTaskRequest(TaskCreateRequest taskCreateRequest){
        return Task.builder()
                .name(taskCreateRequest.getTaskName())
                .taskEnum(taskCreateRequest.getTaskEnum())
                .priority(taskCreateRequest.getPriority())
                .build();
    }

    static TaskCreateResponse createTaskResponse(Task task){
        return TaskCreateResponse.builder()
                .id(task.getId())
                .userId(task.getUser().getId())
                .taskName(task.getName())
                .taskEnum(task.getTaskEnum())
                .priority(task.getPriority())
                .build();
    }
}
