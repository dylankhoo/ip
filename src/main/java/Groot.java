import java.util.Scanner;
import java.util.ArrayList;


public class Groot {

    private static boolean grootRunning;
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static String GREEN = "\u001B[32m";
    public static String RESET = "\033[0m";
    public static String SPACE = "\t";

    public static void printBorder(){
        System.out.println(SPACE + "________________________________________");
    }

    public static void setRunning (boolean grootRunning){
        Groot.grootRunning = grootRunning;
    }

    public static void say(String text){
        printBorder();
        System.out.println(SPACE + "I am Groot!");
        System.out.println(SPACE + GREEN + text + RESET);
        printBorder();
        System.out.print("\n$");
    }

    public static void say(ArrayList<String> text){
        printBorder();
        System.out.println(SPACE + "I am Groot!");
        for(String line : text){
            System.out.println(SPACE + GREEN + line + RESET);
        }
        printBorder();
        System.out.println();
    }

    public static void greet(){
        say("Hello! How may I help?");
    }

    public static void exit(){
        say("Goodbye! See you again soon.");
    }

    public static void addTask(String task){
        say("added: " + task);
        taskList.add(new Task(task));
    }

    public static void listTask(){
        ArrayList<String> taskListText = new ArrayList<>();
        taskListText.add("Here is what you have to do:");
        for(int i = 0; i < taskList.size(); i++){
            taskListText.add(SPACE + (i + 1) + "." + taskList.get(i).getDescription());
        }
        say(taskListText);
    }

    
    //TODO: Handle case where second argument is not an Integer
    public static void handleMark(String command){
        String[] commandParts = command.split(" ");
        if(commandParts.length < 2){
            say("No task number specified!");
            return;
        }
        if(commandParts.length > 2){
            say("Too many arguments!");
            return;
        }
        int taskNum = Integer.parseInt(commandParts[1]);
        if(taskNum < 1 || taskNum > taskList.size()){
                say("Invalid task number!");
                return;
        }
        ArrayList<String> markText = new ArrayList<>();
        if(commandParts[0].equals("mark")){
            taskList.get(taskNum - 1).setDone(true); 
            markText.add("I've marked this task as done.");
        }
        else{
            taskList.get(taskNum - 1).setDone(false);
            markText.add("I've marked this task as not done.");
        }
        markText.add(SPACE + taskList.get(taskNum - 1).getDescription());
        say(markText);
    }

    //TODO: More robust command string handling needed
    public static void handleCommand(String command){
        String[] commandParts = command.split(" ", 2);
        if(commandParts[0].equals("mark") || commandParts[0].equals("unmark")){
            handleMark(command);
            return;
        }
        switch (command) {
            case "bye":
                setRunning(false);
                exit();
                break;
            case "list":
                listTask();
                break;
            default:
                addTask(command);
        }
    }

    public static void main(String[] args) {
        String logo = SPACE + "  ____ ____   ___   ___ _____\n" 
               + SPACE + " / ___|  _ \\ / _ \\ / _ \\_   _|     /\\\n"
               + SPACE + "| |  _| |_) | | | | | | || |      /  \\\n"  
               + SPACE + "| |_| |  _ <| |_| | |_| || |     /____\\\n"  
               + SPACE + " \\____|_| \\_\\\\___/ \\___/ |_|       ||\n";  
        System.out.println(SPACE + "I am\n" + GREEN + logo + RESET);
        greet();
        setRunning(true);
        while(grootRunning){
            Scanner input = new Scanner(System.in);
            handleCommand(input.nextLine());
        }
    }
}  
