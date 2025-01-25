public class Groot {

    public static String GREEN = "\u001B[32m";
    public static String RESET = "\033[0m";
    
    public static void say(String text){
        System.out.println("I am Groot");
        System.out.println(GREEN + text + "\n" + RESET);
    }

    public static void greet(){
        say("Hello! How may I help?\n");
    }

    public static void exit(){
        say("Goodbye! See you again soon.");
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
