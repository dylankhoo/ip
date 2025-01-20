public class Groot {
    public static void greet(){
        System.out.println("I am Groot");
        System.out.println("Hello! How may I help?\n");
    }

    public static void exit(){
        System.out.println("I am Groot");
        System.out.println("Goodbye! See you again soon.\n");
    }

    public static void main(String[] args) {
        String logo ="  ____ ____   ___   ___ _____\n" 
               + " / ___|  _ \\ / _ \\ / _ \\_   _|     /\\\n"
               + "| |  _| |_) | | | | | | || |      /  \\\n"  
               + "| |_| |  _ <| |_| | |_| || |     /____\\\n"  
               + " \\____|_| \\_\\\\___/ \\___/ |_|       ||\n";  
        System.out.println("I am\n" + logo);
        greet();
        exit();
    }
}  
