package se.robin.todomvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskService {
    private TaskRepository repository;

    @Autowired
    public void prepare (TaskRepository repository) {
        this.repository = repository;
    }

    public TodoTask add(int listId) {
        return repository.addTask(new TodoTask(listId));
    }
}
