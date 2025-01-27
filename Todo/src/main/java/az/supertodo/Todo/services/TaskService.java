package az.supertodo.Todo.services;

import az.supertodo.Todo.entities.Task;
import az.supertodo.Todo.entities.User;
import az.supertodo.Todo.mappers.TaskMapper;
import az.supertodo.Todo.repositories.TaskRepository;
import az.supertodo.Todo.repositories.UserRepo;
import az.supertodo.Todo.requests.TaskCreateRequest;
import az.supertodo.Todo.responses.TaskCreateResponse;
import az.supertodo.Todo.usefull.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepo userRepo;

    public SuccessResult<TaskCreateResponse> createNewTask(TaskCreateRequest taskCreateRequest) {
        if(userRepo.getId() != null){
            Task taskRequest = TaskMapper.createTaskRequest(taskCreateRequest);
            taskRequest.setUser(User.builder().id(userRepo.getId()).build());
            System.out.println(userRepo.getId());

            Task newTask = taskRepository.save(taskRequest);

            TaskCreateResponse newResponse = TaskMapper.createTaskResponse(newTask);
            return new SuccessResult<>(newResponse, "Successfully created task.");

        }else{
            throw new RuntimeException("You must log in first");
        }
    }

    public List<Task> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable).getContent();
    }
}
