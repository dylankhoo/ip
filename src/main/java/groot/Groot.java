package groot;

import java.util.Scanner;

import groot.command.Command;
import groot.ui.Ui;
import groot.tasks.*;
import groot.data.Data;

import java.util.ArrayList;
public class Groot {

    private static boolean isRunning;
    private static ArrayList<Task> taskList;

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
        taskList = Data.loadData();
        // Continue to take input while Groot is running
        while (isRunning) {
            Command.handleCommand(input.nextLine(), taskList);
        }
    }
}  
