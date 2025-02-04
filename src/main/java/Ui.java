import java.util.ArrayList;

public class Ui {

    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_RESET = "\033[0m";
    public static final String INDENT = "    ";

    public static void printBorder() {
        System.out.println(INDENT + "________________________________________");
    }

    public static void say(String text) {
        printBorder();
        System.out.println(INDENT + "I am Groot!");
        System.out.println(INDENT + COLOR_GREEN + text + COLOR_RESET);
        printBorder();
        if (Groot.isRunning()) {
            System.out.print("\n$ ");
        }
    }

    public static void say(ArrayList<String> text) {
        printBorder();
        System.out.println(INDENT + "I am Groot!");
        for (String line : text) {
            System.out.println(INDENT + COLOR_GREEN + line + COLOR_RESET);
        }
        printBorder();
        if (Groot.isRunning()) {
            System.out.print("\n$ ");
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
        String logo = INDENT + "  ____ ____   ___   ___ _____\n" 
                + INDENT + " / ___|  _ \\ / _ \\ / _ \\_   _|     /\\\n"
                + INDENT + "| |  _| |_) | | | | | | || |      /  \\\n"  
                + INDENT + "| |_| |  _ <| |_| | |_| || |     /____\\\n"  
                + INDENT + " \\____|_| \\_\\\\___/ \\___/ |_|       ||\n";  
        System.out.println(INDENT + "I am\n" + COLOR_GREEN + logo + COLOR_RESET);
    }
}