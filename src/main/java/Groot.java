import java.util.Scanner;
import java.util.ArrayList;
public class Groot {

    private static boolean isRunning;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void setRunning(boolean isRunning) {
        Groot.isRunning = isRunning;
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static void addTask(String[] commandParts) {
        String taskType;
        String taskDescription;

        // Check if valid command 
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

    public static void listTask() {
        ArrayList<String> taskListText = new ArrayList<>();
        taskListText.add("Here is what you have to do:");
        for (int i = 0; i < taskList.size(); i++) {
            taskListText.add(Ui.INDENT + (i + 1) + "." + taskList.get(i).getDescription());
        }
        taskListText.add("You have " + taskList.size() + " left");
        Ui.say(taskListText);
    }

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
            int taskNum = Integer.parseInt(commandParts[1]);
            if (taskNum < 1 || taskNum > taskList.size()) {
                    Ui.say("Invalid task number!");
                    return;
            }

            ArrayList<String> markText = new ArrayList<>();
            if (commandParts[0].equals("mark")) {
                taskList.get(taskNum - 1).setDone(true); 
                markText.add("I've marked this task as done.");
            } else {
                taskList.get(taskNum - 1).setDone(false);
                markText.add("I've marked this task as not done.");
            }
            markText.add(Ui.INDENT + taskList.get(taskNum - 1).getDescription());
            Ui.say(markText);
        } catch (NumberFormatException e) {
            Ui.say("Task number must be an integer!");
        }
    }

    //TODO: More robust command string handling needed
    public static void handleCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        if (commandParts[0].matches("(mark)||(unmark)")) {
            handleMark(command);
            return;
        }
        switch (command) {
        case "bye":
            Ui.exit();
            break;
        case "list":
            listTask();
            break;
        default:
            addTask(commandParts);
            break;
        }
    }

    public static void main(String[] args) {
        Ui.printLogo();
        setRunning(true);
        Ui.greet();
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            handleCommand(input.nextLine());
        }
    }
}  
