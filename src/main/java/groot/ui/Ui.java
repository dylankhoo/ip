package groot.ui;

import java.util.ArrayList;

import groot.Groot;
import groot.tasks.Task;

public class Ui {

    // UI constants for indenting and printing text in green
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_RESET = "\033[0m";
    public static final String INDENT = "    ";
    public static final String NEW_LINE = System.lineSeparator();

    public static void printBorder() {
        System.out.println(INDENT + "________________________________________");
    }

    // say() method formats text before printing 
    public static void say(String text) {
        printBorder();
        System.out.println(INDENT + "I am Groot!");
        System.out.println(INDENT + COLOR_GREEN + text + COLOR_RESET);
        printBorder();
        if (Groot.isRunning()) {
            System.out.print(NEW_LINE + "$ ");
        }
    }

    // Overload to support use of ArrayList
    public static void say(ArrayList<String> text) {
        printBorder();
        System.out.println(INDENT + "I am Groot!");
        for (String line : text) {
            System.out.println(INDENT + COLOR_GREEN + line + COLOR_RESET);
        }
        printBorder();
        if (Groot.isRunning()) {
            System.out.print(NEW_LINE + "$ ");
        }
    }

    public static void greet() {
        say("Hello! How may I help?");
    }

    public static void exit() {
        Groot.setRunning(false);
        say("Goodbye! See you again soon.");
    }

    public static void printLogo() {
        String logo = INDENT + "  ____ ____   ___   ___ _____" + NEW_LINE 
                + INDENT + " / ___|  _ \\ / _ \\ / _ \\_   _|     /\\" + NEW_LINE
                + INDENT + "| |  _| |_) | | | | | | || |      /  \\" + NEW_LINE  
                + INDENT + "| |_| |  _ <| |_| | |_| || |     /____\\" + NEW_LINE  
                + INDENT + " \\____|_| \\_\\\\___/ \\___/ |_|       ||" + NEW_LINE;  
        System.out.println(COLOR_GREEN + logo + COLOR_RESET);
    }

    public static void listTasks(ArrayList<Task> taskList) {
        ArrayList<String> taskListText = new ArrayList<>();
        taskListText.add("Here is what you have to do:");
        for (int i = 0; i < taskList.size(); i++) {
            // Add all tasks descriptions to the taskListText
            taskListText.add(Ui.INDENT + (i + 1) + "." + taskList.get(i).getDescription());
        }
        taskListText.add("You have " + taskList.size() + " tasks");
        say(taskListText);
    }
}