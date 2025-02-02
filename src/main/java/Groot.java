import java.util.Scanner;
import java.util.ArrayList;
public class Groot {

    private static boolean isRunning;
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_RESET = "\033[0m";
    public static final String INDENT = "    ";

    public static void printBorder() {
        System.out.println(INDENT + "________________________________________");
    }

    public static void setRunning(boolean isRunning) {
        Groot.isRunning = isRunning;
    }

    public static void say(String text) {
        printBorder();
        System.out.println(INDENT + "I am Groot!");
        System.out.println(INDENT + COLOR_GREEN + text + COLOR_RESET);
        printBorder();
        if (isRunning) {
            System.out.print("\n$");
        }
    }

    public static void say(ArrayList<String> text) {
        printBorder();
        System.out.println(INDENT + "I am Groot!");
        for (String line : text) {
            System.out.println(INDENT + COLOR_GREEN + line + COLOR_RESET);
        }
        printBorder();
        System.out.print("\n$ ");
    }

    public static void greet() {
        say("Hello! How may I help?");
    }

    public static void exit() {
        setRunning(false);
        say("Goodbye! See you again soon.");
    }

    public static void addTask(String[] commandParts) {
        String taskType;
        String taskDescription;

        // Check if valid command 
        if (commandParts.length < 2) {
            if(commandParts[0].matches("(todo)|(deadline)|(event)")) {
                say("Missing task description!");
                return;
            }
            if(commandParts[0].equals("")) {
                say("You didn't say anything!");
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
        say("added: " + taskDescription);
    }

    public static void listTask() {
        ArrayList<String> taskListText = new ArrayList<>();
        taskListText.add("Here is what you have to do:");
        for (int i = 0; i < taskList.size(); i++) {
            taskListText.add(INDENT + (i + 1) + "." + taskList.get(i).getDescription());
        }
        taskListText.add("You have " + taskList.size() + " left");
        say(taskListText);
    }

    public static void handleMark(String command) {
        String[] commandParts = command.split(" ");
        if (commandParts.length < 2) {
            say("No task number specified!");
            return;
        }
        if (commandParts.length > 2) {
            say("Too many arguments!");
            return;
        }
        try {
            int taskNum = Integer.parseInt(commandParts[1]);
            if (taskNum < 1 || taskNum > taskList.size()) {
                    say("Invalid task number!");
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
            markText.add(INDENT + taskList.get(taskNum - 1).getDescription());
            say(markText);
        } catch (NumberFormatException e) {
            say("Task number must be an integer!");
        }
    }

    //TODO: More robust command string handling needed
    public static void handleCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        if (commandParts[0].equals("mark") || commandParts[0].equals("unmark")) {
            handleMark(command);
            return;
        }
        switch (command) {
        case "bye":
            exit();
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
        String logo = INDENT + "  ____ ____   ___   ___ _____\n" 
                + INDENT + " / ___|  _ \\ / _ \\ / _ \\_   _|     /\\\n"
                + INDENT + "| |  _| |_) | | | | | | || |      /  \\\n"  
                + INDENT + "| |_| |  _ <| |_| | |_| || |     /____\\\n"  
                + INDENT + " \\____|_| \\_\\\\___/ \\___/ |_|       ||\n";  
        System.out.println(INDENT + "I am\n" + COLOR_GREEN + logo + COLOR_RESET);
        setRunning(true);
        greet();
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            handleCommand(input.nextLine());
        }
    }
}  
