package az.supertodo.Todo.repositories;

import az.supertodo.Todo.Enums.TaskEnum;
import az.supertodo.Todo.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
