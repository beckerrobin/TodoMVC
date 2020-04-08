package se.robin.todomvc;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {
    private TaskService taskService;
    private ListService listService;

    public TodoController(TaskService taskService, ListService listService) {
        this.taskService = taskService;
        this.listService = listService;
    }

    @RequestMapping("/")
    public String start() {
        return "index";
    }

    @GetMapping("/api/tasks/get")
    @ResponseBody
//    public ResponseEntity<List<TodoTask>> getTasks(@RequestBody ListData list) {
    public ResponseEntity<List<TodoTask>> getTasks() {
        List<TodoTask> tasks = taskService.getTasks(1);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/api/tasks/getsingle")
    @ResponseBody
    public ResponseEntity<TodoTask> getSingleTask(@RequestBody TaskData data) {
        TodoTask task = taskService.getSingleTask(data.getTaskId());
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/api/tasks/delete")
    @ResponseBody
    public ResponseEntity<Integer> deleteTask(@RequestBody TaskData taskId) {
        Integer deleted = taskService.deleteTask(taskId.getTaskId());
        return ResponseEntity.ok(deleted);
    }

    @PostMapping("/api/tasks/add")
    public ResponseEntity<TodoTask> addTask(@RequestBody TaskData task) {
        TodoTask added = taskService.addTask(task.getContent());
        if (added == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(added);
    }

    @PutMapping("/api/tasks/update")
    public ResponseEntity<TodoTask> updateTask(@RequestBody TaskData task) {
        System.out.println(task);
        TodoTask updated = taskService.updateTask(task);
        if (updated == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/api/lists/add")
    public ResponseEntity<TodoList> addList() {
        TodoList added = listService.add();
        if (added == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(added);
    }

}
