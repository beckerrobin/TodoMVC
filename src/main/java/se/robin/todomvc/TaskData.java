package se.robin.todomvc;

public class TaskData {
    private int listId;
    private int taskId;
    private String content;
    private Boolean completed;

    public String getContent() {
        return content;
    }

    public int getTaskId() {
        return taskId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "listId=" + listId +
                ", taskId=" + taskId +
                ", content='" + content + '\'' +
                ", isCompleted=" + completed +
                '}';
    }
}
