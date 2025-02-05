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

        

    public static void main(String[] args) {
        Ui.printLogo();
        setRunning(true);
        Ui.greet();
        Scanner input = new Scanner(System.in);

        // Continue to take input while Groot is running
        while (isRunning) {
            Command.handleCommand(input.nextLine(), taskList);
        }
    }
}  
