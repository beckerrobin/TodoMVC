package se.robin.todomvc.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.robin.todomvc.model.TodoTask;
import se.robin.todomvc.service.TaskService;

import java.util.List;

@Controller
public class TodoController {
    private TaskService taskService;

    public TodoController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Visar index.html
     */
    @RequestMapping("/")
    public String start() {
        return "index";
    }

    /**
     * Get all tasks from database
     *
     * @return List of TodoTask-objects
     */
    @GetMapping("/api/tasks/get")
    @ResponseBody
    public ResponseEntity<List<TodoTask>> getTasks() {
        List<TodoTask> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Get a single task with the specified taskId
     *
     * @param data Object containing taskId
     * @return Single TodoTask-object
     */
    @GetMapping("/api/tasks/getsingle")
    @ResponseBody
    public ResponseEntity<TodoTask> getSingleTask(@RequestBody TaskData data) {
        TodoTask task = taskService.getSingleTask(data.getTaskId());
        return ResponseEntity.ok(task);
    }

    /**
     * Delete single task
     *
     * @param taskId Task to be removed
     * @return no of rows removed from database
     */
    @DeleteMapping("/api/tasks/delete")
    @ResponseBody
    public ResponseEntity<Integer> deleteTask(@RequestBody TaskData taskId) {
        Integer deleted = taskService.deleteTask(taskId.getTaskId());
        return ResponseEntity.ok(deleted);
    }

    /**
     * Add a new task
     *
     * @param task Data containing content of task to be created
     * @return The newly created object
     */
    @PostMapping("/api/tasks/add")
    @ResponseBody
    public ResponseEntity<TodoTask> addTask(@RequestBody TaskData task) {
        TodoTask added = taskService.addTask(task.getContent());
        if (added == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(added);
    }

    /**
     * Update an existing task in the database
     *
     * @param task The new task-object to be updated based on taskId
     * @return Newly created TodoTask-object
     */
    @PutMapping("/api/tasks/update")
    @ResponseBody
    public ResponseEntity<TodoTask> updateTask(@RequestBody TaskData task) {
        TodoTask updated = taskService.updateTask(task);
        if (updated == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(updated);
    }
}
