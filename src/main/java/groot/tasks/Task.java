package groot.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String key;

    public Task(String description) {
        this.description = description;
        this.key = description;
        isDone = false;
        taskType = "N";
    }

    public static Task createTask(String description) {
        return new Task(description.trim());
    }

    public String getDescription() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getKey() {
        return taskType + " | " + (isDone ? "1" : "0") + " | " + key;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
