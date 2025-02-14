package groot.command;

import java.util.ArrayList;

import groot.ui.Ui;
import groot.tasks.Task;
import groot.tasks.TaskManager;

public class Command {

    public static void handleCommand(String command, ArrayList<Task> taskList) {
        try {
            String[] commandParts = command.split(" ", 2);

            /* Cases mark, unmark and delete are handled separately first as using first argument 
            to select switch case has potential issues.
            e.g. "List out everything to my father" should be a task, not a "list" command */        
            if (commandParts[0].matches("(mark)||(unmark)")) {
                handleMark(command, taskList);
                return;
            }
    
            switch (commandParts[0]) {
            case "bye":
                Ui.exit();
                break;
            case "list":
                Ui.listTasks(taskList);
                break;
            case "delete":
                delete(command, taskList);
                break;
            default:
                TaskManager.addTask(commandParts, taskList);
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.say("Sorry! I'm not quite sure what you want me to do.");
        }
    }

    public static void handleMark(String command, ArrayList<Task> taskList) {
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
            
            ArrayList<String> markText = new ArrayList<>();
            if (commandParts[0].equals("mark")) {
                // Command "mark"
                taskList.get(taskNum)
                        .setDone(true); 
                markText.add("I've marked this task as done.");
            } else {
                // Command "unmark"
                taskList.get(taskNum)
                        .setDone(false);
                markText.add("I've marked this task as not done.");
            }

            markText.add(Ui.INDENT + taskList.get(taskNum).getDescription());
            Ui.say(markText);
        } catch (NumberFormatException e) {
            Ui.say("Task number must be an integer!");
        }
    }

    public static void delete(String command, ArrayList<Task> taskList) {
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
}