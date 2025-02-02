public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static Todo createTodo(String description) {
        return new Todo(description.trim());
    }

    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
}