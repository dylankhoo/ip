package groot.tasklist;

import java.util.ArrayList;

import groot.storage.Storage;
import groot.tasks.Deadline;
import groot.tasks.Event;
import groot.tasks.Task;
import groot.tasks.Todo;
import groot.ui.Ui;

public class TaskList {
    public static ArrayList<Task> taskList;

    public static void begin() {
        taskList = Storage.loadData();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    public static int getSize() {
        return taskList.size();
    }
    
    public static void delete(String command) {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            Ui.say("No task number specified!");
            return;
        }
        if (commandParts.length > 2) {
            Ui.say("Too many arguments!");
            return;
        }
        try {
            // taskNum is 1 less than user input as 0 index
            int taskNum = Integer.parseInt(commandParts[1]) - 1;

            // Guard to check if taskNum is valid
            if (taskNum < 0 || taskNum >= taskList.size()) {
                    Ui.say("Invalid task number!");
                    return;
            }
            
            ArrayList<String> deleteText = new ArrayList<>();
            deleteText.add("I have deleted this task:");
            deleteText.add(Ui.INDENT + taskList.get(taskNum).getDescription());
            taskList.remove(taskNum);
            deleteText.add("You now have " + taskList.size() + " tasks");
            Ui.say(deleteText);

        } catch (NumberFormatException e) {
            Ui.say("Task number must be an integer!");
        }
    }

    public static void addTask(String[] commandParts) {
        String taskType;
        String taskDescription;

        // Check for valid command. A command with < 2 Arguments is invalid 
        if (commandParts.length < 2) {
            if (commandParts[0].matches("(todo)|(deadline)|(event)")) {
                Ui.say("Missing task description!");
                return;
            } else if (commandParts[0].equals("")) {
                Ui.say("You didn't say anything!");
                return;
            } else {
                Ui.say("Sorry! I'm not quite sure what you want me to do.");
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
                    break;
                } 
                return;

            case "deadline":
                Deadline deadlineTask = Deadline.createDeadline(taskDescription);
                if (deadlineTask != null) {
                    taskList.add(deadlineTask);
                    break;
                } 
                return;
                
            case "event":
                Event eventTask = Event.createEvent(taskDescription);
                if (eventTask != null) {
                    taskList.add(eventTask);
                    break;
                } 
                return;
        
            case "task":
                Task task = Task.createTask(taskDescription);
                if (task != null) {
                    taskList.add(task);
                    break;
                } 
                return;
                
            default:
                Ui.say("Sorry! I'm not quite sure what you want me to do.");
                return;
            }
        }

        // Last element of TaskList represents the new task
        // Get last element and say what was added to the list
        Ui.say("added: " + taskList.get(taskList.size() - 1).getDescription());
    }
}