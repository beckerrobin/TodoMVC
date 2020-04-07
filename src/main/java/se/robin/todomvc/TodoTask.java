package se.robin.todomvc;

import javax.persistence.*;

@Entity
@Table(name ="tasks")
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId", nullable = false)
    private int taskId;

    @Column(name = "taskOrder", nullable = false)
    private int order;

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

    public int getTaskId() {
        return taskId;
    }

    public int getOrder() {
        return order;
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
                ", order=" + order +
                ", content='" + content + '\'' +
                ", isCompleted=" + isCompleted +
                ", listId=" + listId +
                '}';
    }
}
