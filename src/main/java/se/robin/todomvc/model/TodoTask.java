package se.robin.todomvc.model;

import javax.persistence.*;

@Entity
@Table(name ="tasks")
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId", nullable = false)
    private int taskId;

    @Column(name = "content", nullable = false)
    private String content = "";

    @Column(name = "completed", nullable = false)
    private Boolean completed = false;

    @Column(name = "listId", nullable = false)
    private int listId = 1;

    public TodoTask() {
    }

    public TodoTask(String content) {
        this.content = content;
    }

    public TodoTask(int taskId, String content, boolean completed) {
        this.content = content;
        this.taskId = taskId;
        this.completed = completed;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getContent() {
        return content;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public int getListId() {
        return listId;
    }

    @Override
    public String toString() {
        return "TodoTask{" +
                "taskId=" + taskId +
                ", content='" + content + '\'' +
                ", completed=" + completed +
                ", listId=" + listId +
                '}';
    }
}
