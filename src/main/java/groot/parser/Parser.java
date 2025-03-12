package groot.parser;

import java.util.ArrayList;

import groot.ui.Ui;
import groot.storage.Storage;
import groot.tasklist.TaskList;

public class Parser {

    /** 
     * Parses user input to check if valid command. 
     * Calls corresponding method based on command.
     * Writes to storage after if a corresponding method is called.
     */     
    public static void handleCommand(String command) {
        try {
            String[] commandParts = command.split(" ", 2);

            /** 
             * Cases mark, unmark and delete are handled separately first as using first argument 
             * to select switch case has potential issues.
             * e.g. "List out everything to my father" should be a task, not a "list" command 
             */      

            if (commandParts[0].matches("(mark)||(unmark)")) {
                handleMark(command);
                Storage.writeData();
                return;
            }
    
            switch (commandParts[0]) {
            case "bye":
                Ui.exit();
                break;
            case "list":
                Ui.listTasks();
                break;
            case "delete":
                TaskList.delete(command);
                break;
            case "find":
                TaskList.findTask(commandParts[1]);
                break;
            default:
                TaskList.addTask(commandParts);
                break;
            }
            Storage.writeData();
        } catch (IndexOutOfBoundsException e) {
            Ui.say("Sorry! I'm not quite sure what you want me to do.");
        }
    }

    /** 
     * Method that to handle marking and unmarking of tasks
     * @param command the unparsed mark or unmark command, inclusive of task index 
     */     

    public static void handleMark(String command) {
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
            if (taskNum < 0 || taskNum >= TaskList.getSize()) {
                    Ui.say("Invalid task number!");
                    return;
            }
            
            ArrayList<String> markedTexts = new ArrayList<>();
            if (commandParts[0].equals("mark")) {
                // Command "mark"
                TaskList.getTaskList()
                        .get(taskNum)
                        .setDone(true); 
                markedTexts.add("I've marked this task as done.");
            } else {
                // Command "unmark"
                TaskList.getTaskList()
                        .get(taskNum)
                        .setDone(false);
                markedTexts.add("I've marked this task as not done.");
            }

            markedTexts.add(Ui.INDENT + TaskList.getTaskList().get(taskNum).getDescription());
            Ui.say(markedTexts);
        } catch (NumberFormatException e) {
            Ui.say("Task number must be an integer!");
        }
    }
}