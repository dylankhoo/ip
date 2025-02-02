public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public static Task createTask(String description) {
        return new Task(description.trim());
    }

    public String getDescription() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
