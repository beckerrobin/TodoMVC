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

    /**
     * Add a new task
     * @param content Content of task to be created
     * @return The newly created object
     */
    @Transactional
    public TodoTask addTask(String content) {
        return repository.addTask(new TodoTask(content));
    }

    /**
     * Get all tasks from database
     * @return List of TodoTask-objects
     */
    @Transactional(readOnly = true)
    public List<TodoTask> getTasks() {
        return repository.getTasks();
    }

    /**
     * Get a single task with the specified taskId
     * @param taskId Id of task to be returned
     * @return Single TodoTask-object
     */
    @Transactional(readOnly = true)
    public TodoTask getSingleTask(int taskId) {
        return repository.getSingleTask(taskId);
    }

    /**
     * Delete single task
     * @param taskId Task to be removed
     * @return no of rows removed from database
     */
    @Transactional
    public int deleteTask(int taskId) {
        return repository.deleteTask(taskId);
    }

    /**
     * Update an existing task in the database
     * @param task The new task-object to be updated based on taskId
     * @return Newly created TodoTask-object
     */
    @Transactional
    public TodoTask updateTask(TaskData task) {
        return repository.updateTask(new TodoTask(task.getTaskId(), task.getContent(), task.getCompleted()));
    }
}
