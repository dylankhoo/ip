import java.util.ArrayList;

public class TaskManager {

    public static void addTask(String[] commandParts, ArrayList<Task> taskList) {
        String taskType;
        String taskDescription;

        // Check for valid command. A command with < 2 Arguments is either invalid or regular Task 
        if (commandParts.length < 2) {
            if(commandParts[0].matches("(todo)|(deadline)|(event)")) {
                Ui.say("Missing task description!");
                return;
            }
            if(commandParts[0].equals("")) {
                Ui.say("You didn't say anything!");
                return;
            }
            // If commandParts is of length 1 and not (todo|deadline|event), add it as a regular task
            taskDescription = commandParts[0];
            taskList.add(Task.createTask(taskDescription));
        } else {

            taskType = commandParts[0];
            taskDescription = commandParts[1];

            switch (taskType) {
            case "todo":
                taskList.add(Todo.createTodo(taskDescription));
                break;
            case "deadline":
                taskList.add(Deadline.createDeadline(taskDescription));
                break;
            case "event":
                taskList.add(Event.createEvent(taskDescription));
                break;
            }
        }
        Ui.say("added: " + taskDescription);
    }
}