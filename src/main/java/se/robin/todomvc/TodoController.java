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
    public ResponseEntity<TodoTask> getSingleTask(@RequestBody ListData list) {
        TodoTask task = taskService.getSingleTask(list.getListId());
        return ResponseEntity.ok(task);
    }

    @PostMapping("/api/tasks/add")
    public ResponseEntity<TodoTask> AddTask(@RequestBody TaskData task) {
        TodoTask added = taskService.add(task.getListId());
        if (added == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(added);
    }

    @PostMapping("/api/lists/add")
    public ResponseEntity<TodoList> AddList() {
        TodoList added = listService.add();
        if (added == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(added);
    }

}
