package se.robin.todomvc;

public class TaskData {
    private int listId;
    private int taskId;
    private String content;

    public String getContent() {
        return content;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
