package se.robin.todomvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskService {
    private TaskRepository repository;

    @Autowired
    public void prepare(TaskRepository repository) {
        this.repository = repository;
    }

    public TodoTask add(int listId) {
        return repository.addTask(new TodoTask(listId));
    }

    public List<TodoTask> getTasks(int listId) {
        return repository.getTasks(listId);
    }
    public TodoTask getSingleTask(int listId) {
        return repository.getSingleTask(listId);
    }
}
