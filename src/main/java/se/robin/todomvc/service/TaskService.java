package se.robin.todomvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.robin.todomvc.model.TodoTask;
import se.robin.todomvc.repository.TaskRepository;
import se.robin.todomvc.web.TaskData;

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
    public TodoTask getSingleTask(int taskId) {
        return repository.getSingleTask(taskId);
    }

    @Transactional
    public int deleteTask(int taskId) {
        return repository.deleteTask(taskId);
    }

    @Transactional
    public TodoTask updateTask(TaskData task) {
        return repository.updateTask(new TodoTask(task.getTaskId(), task.getContent(), task.getCompleted()));
    }
}
