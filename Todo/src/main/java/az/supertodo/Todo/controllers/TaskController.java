package az.supertodo.Todo.controllers;

import az.supertodo.Todo.entities.Task;
import az.supertodo.Todo.requests.TaskCreateRequest;
import az.supertodo.Todo.responses.TaskCreateResponse;
import az.supertodo.Todo.services.TaskService;
import az.supertodo.Todo.usefull.SuccessResult;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public SuccessResult<TaskCreateResponse> saveTask(@RequestBody @Valid
                                                          TaskCreateRequest taskCreateRequest){
        return taskService.createNewTask(taskCreateRequest);
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "2") int size){
        return taskService.getAllTasks(page, size);
    }

}
