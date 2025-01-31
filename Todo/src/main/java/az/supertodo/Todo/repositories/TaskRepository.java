package az.supertodo.Todo.repositories;

import az.supertodo.Todo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
