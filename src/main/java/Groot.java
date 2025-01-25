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
        printBorder();
        System.out.println(SPACE + "I am Groot!");
        for(int i = 0; i < taskList.size(); i++){
            System.out.println(SPACE + GREEN + (i + 1) + "." + taskList.get(i).getDescription() + RESET);
        }
    }

    public static void handleCommand(String command){
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
