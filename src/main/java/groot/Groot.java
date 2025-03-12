package groot;

import java.util.Scanner;

import groot.parser.Parser;
import groot.storage.Storage;
import groot.tasklist.TaskList;
import groot.ui.Ui;
import groot.tasks.*;

import java.util.ArrayList;
public class Groot {

    private static boolean isRunning;

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
        TaskList.begin();
        // Continue to take input while Groot is running
        while (isRunning) {
            Parser.handleCommand(input.nextLine());
        }
    }
}  
