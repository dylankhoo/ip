import java.util.ArrayList;

public class Command {

    public static void handleCommand(String command, ArrayList<Task> taskList) {
        String[] commandParts = command.split(" ", 2);

        /* Cases mark and unmark are handled separately first as using first argument 
        to select switch case has potential issues.
        e.g. "List out everything to my father" should be a task, not a "list" command */        
        if (commandParts[0].matches("(mark)||(unmark)")) {
            handleMark(command, taskList);
            return;
        }

        switch (command) {
        case "bye":
            Ui.exit();
            break;
        case "list":
            Ui.listTasks(taskList);
            break;
        default:
            TaskManager.addTask(commandParts, taskList);
            break;
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
}