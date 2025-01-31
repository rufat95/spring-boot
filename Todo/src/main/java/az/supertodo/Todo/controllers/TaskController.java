package az.supertodo.Todo.controllers;

import az.supertodo.Todo.entities.Task;
import az.supertodo.Todo.requests.TaskCreateRequest;
import az.supertodo.Todo.requests.TaskUpdateRequest;
import az.supertodo.Todo.responses.TaskCreateResponse;
import az.supertodo.Todo.responses.TaskUpdateResponse;
import az.supertodo.Todo.services.TaskService;
import az.supertodo.Todo.usefull.Result;
import az.supertodo.Todo.usefull.SuccessResult;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public SuccessResult<TaskCreateResponse> saveTask(@RequestBody @Valid
                                                      TaskCreateRequest taskCreateRequest) {
        return taskService.createNewTask(taskCreateRequest);
    }

    @GetMapping
    public Result<TaskUpdateResponse> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "2") int size) {
        return taskService.getAllTasks(page, size);
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    public SuccessResult<TaskUpdateResponse> taskUpdate(
            @PathVariable Long taskId,
            @RequestBody @Valid TaskUpdateRequest taskUpdateRequest) {
        return taskService.taskUpdate(taskId, taskUpdateRequest);
    }

    @DeleteMapping("/{taskId}")
    public SuccessResult<TaskUpdateResponse> deleteTask(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }

    @GetMapping("/plus")
    public Integer plusFunc(int a, int b){
        return taskService.plusFunc(a, b);
    }

}
