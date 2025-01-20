public class Groot {

    public static String GREEN = "\u001B[32m";
    public static String RESET = "\033[0m";

    public static void greet(){
        System.out.println("I am Groot");
        System.out.println(GREEN + "Hello! How may I help?\n" + RESET);
    }

    public static void exit(){
        System.out.println("I am Groot");
        System.out.println(GREEN + "Goodbye! See you again soon.\n" + RESET);
    }

    public static void main(String[] args) {
        String logo ="  ____ ____   ___   ___ _____\n" 
               + " / ___|  _ \\ / _ \\ / _ \\_   _|     /\\\n"
               + "| |  _| |_) | | | | | | || |      /  \\\n"  
               + "| |_| |  _ <| |_| | |_| || |     /____\\\n"  
               + " \\____|_| \\_\\\\___/ \\___/ |_|       ||\n";  
        System.out.println("I am\n" + GREEN + logo + RESET);
        greet();
        exit();
    }
}  
