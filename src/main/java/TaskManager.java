import java.util.ArrayList;

public class TaskManager {

    public static void addTask(String[] commandParts, ArrayList<Task> taskList) {
        String taskType;
        String taskDescription;

        // Check for valid command. A command with < 2 Arguments is invalid 
        if (commandParts.length < 2) {
            if(commandParts[0].matches("(todo)|(deadline)|(event)")) {
                Ui.say("Missing task description!");
                return;
            }
            if(commandParts[0].equals("")) {
                Ui.say("You didn't say anything!");
                return;
            }
        } else {

            taskType = commandParts[0].toLowerCase();
            taskDescription = commandParts[1];

            switch (taskType) {
                case "todo":
                Todo todoTask = Todo.createTodo(taskDescription);
                if (todoTask != null) {
                    taskList.add(todoTask);
                } 
                break;
                
            case "deadline":
                Deadline deadlineTask = Deadline.createDeadline(taskDescription);
                if (deadlineTask != null) {
                    taskList.add(deadlineTask);
                } 
                break;
                
            case "event":
                Event eventTask = Event.createEvent(taskDescription);
                if (eventTask != null) {
                    taskList.add(eventTask);
                } 
                break;
        
            case "task":
                Task task = Task.createTask(taskDescription);
                if (task != null) {
                    taskList.add(task);
                } 
                break;
            default:
                Ui.say("Sorry! I'm not quite sure what you want me to do.");
            }
        }

        // Last element of TaskList represents the new task
        // Get last element and say what was added to the list
        Ui.say("added: " + taskList.get(taskList.size() - 1).getDescription());
    }
}