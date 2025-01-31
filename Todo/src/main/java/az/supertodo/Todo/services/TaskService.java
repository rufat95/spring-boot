package az.supertodo.Todo.services;

import az.supertodo.Todo.Enums.StatusCode;
import az.supertodo.Todo.entities.Task;
import az.supertodo.Todo.entities.User;
import az.supertodo.Todo.exception.BaseException;
import az.supertodo.Todo.mappers.TaskMapper;
import az.supertodo.Todo.repositories.TaskRepository;
import az.supertodo.Todo.repositories.UserRepo;
import az.supertodo.Todo.requests.TaskCreateRequest;
import az.supertodo.Todo.requests.TaskUpdateRequest;
import az.supertodo.Todo.responses.TaskCreateResponse;
import az.supertodo.Todo.responses.TaskUpdateResponse;
import az.supertodo.Todo.usefull.Result;
import az.supertodo.Todo.usefull.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepo userRepo;

    public SuccessResult<TaskCreateResponse> createNewTask(
            TaskCreateRequest taskCreateRequest) {
        if (userRepo.getId() != null) {
            Task taskRequest = TaskMapper.createTaskRequest(taskCreateRequest);
            taskRequest.setUser(User.builder().id(userRepo.getId()).build());
            Task newTask = taskRepository.save(taskRequest);
            TaskCreateResponse newResponse = TaskMapper.createTaskResponse(newTask);
            return new SuccessResult<>(newResponse, "Successfully created task.");
        } else {
            throw new RuntimeException("You must log in first");
        }
    }

    public Result<TaskUpdateResponse> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = taskRepository.findAll(pageable);

        List<TaskUpdateResponse> taskResponse = tasks.stream()
                .map(TaskMapper::updateTaskResponse)
                .toList();
        return new Result<>(taskResponse, page, size, tasks.getTotalPages());
    }


    public SuccessResult<TaskUpdateResponse> taskUpdate(
            Long taskId,
            TaskUpdateRequest taskUpdateRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new RuntimeException("That task not found in db."));
        if (userRepo.getId() != null && userRepo.getId().equals(task.getUser().getId())) {
            task.setName(taskUpdateRequest.getTaskName());
            task.setPriority(taskUpdateRequest.getPriority());
            task.setTaskEnum(taskUpdateRequest.getTaskEnum());
            Task updatedTask = taskRepository.save(task);
            TaskUpdateResponse newResponse = TaskMapper.updateTaskResponse(updatedTask);

            return new SuccessResult<>(newResponse, "Task updated Successfully.");
        }else {
            throw new RuntimeException("Please log in first or this is not yor task.");
        }
    }

    public SuccessResult<TaskUpdateResponse> deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() ->
                new RuntimeException("That task not found in db."));
        TaskUpdateResponse taskUpdateResponse = TaskMapper.updateTaskResponse(task);
        if (userRepo.getId() != null && userRepo.getId().equals(task.getUser().getId())) {
            taskRepository.deleteById(task.getId());
            return new SuccessResult<>(taskUpdateResponse, "Task deleted Successfully.");
        }else {
            throw new RuntimeException("Please log in first or this is not yor task.");
        }
    }

    public Integer plusFunc(int a, int b){
        return a/b;
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, StatusCode.TASK_NOT_FOUND));
    }
}
