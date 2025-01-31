package az.supertodo.Todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  {

    @Autowired
    private TaskService taskService;

//    public void run(String... args) throws Exception {
//        taskService.createDummyTasks(); // When program start will create 100 task
//    }

//    public void createDummyTasks() {
//        List<Task> tasks = new ArrayList<>();
//        User user = userRepository.findById(10L).
//        orElseThrow(() -> new RuntimeException("User not found")); // User id = 5
//
//        for (int i = 1; i <= 100; i++) {
//            Task task = new Task();
//            task.setUser(user);
//            task.setName("Rufat Task");
//            task.setTaskEnum(TaskEnum.MEDIUM);
//            task.setPriority("pending yet");
//            tasks.add(task);
//        }
//
//        taskRepository.saveAll(tasks); // it will create 100 task
//    }
}
