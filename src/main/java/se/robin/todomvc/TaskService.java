package se.robin.todomvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TaskService {
    private TaskRepository repository;

    @Autowired
    public void prepare(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TodoTask addTask(String content) {
        return repository.addTask(new TodoTask(content));
    }

    @Transactional(readOnly = true)
    public List<TodoTask> getTasks(int listId) {
        return repository.getTasks(listId);
    }

    @Transactional(readOnly = true)
    public TodoTask getSingleTask(int listId) {
        return repository.getSingleTask(listId);
    }

    @Transactional
    public int deleteTask(int taskId) {
        return repository.deleteTask(taskId);
    }
}
