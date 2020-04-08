package se.robin.todomvc;

import javax.persistence.*;

@Entity
@Table(name ="tasks")
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId", nullable = false)
    private int taskId;

    @Column(name = "content", nullable = false, length = 255)
    private String content = "";

    @Column(name = "isCompleted", nullable = false)
    private boolean isCompleted = false;

    @Column(name = "listId", nullable = false)
    private int listId;

    public TodoTask() {
    }

    public TodoTask(int listId) {
        this.listId = listId;
    }

    public TodoTask(String content) {
        this.content = content;
        this.listId = 1;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getListId() {
        return listId;
    }

    @Override
    public String toString() {
        return "TodoTask{" +
                "taskId=" + taskId +
                ", content='" + content + '\'' +
                ", isCompleted=" + isCompleted +
                ", listId=" + listId +
                '}';
    }
}
