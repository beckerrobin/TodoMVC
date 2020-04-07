package se.robin.todomvc;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
