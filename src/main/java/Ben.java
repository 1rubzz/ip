import java.util.Scanner;

public class Ben {
    public static final String horizontal_lines = "----------------------------------------";

    public static void main(String[] args) {
        printGreeting();

        // Have a new scanner
        Scanner scanner = new Scanner(System.in);

        echo(scanner);
        bidFarewell();
        scanner.close();
    }


    // echos prints messages to the screen
    private static void echo(Scanner s){
        while (true) {
            // scans the entire line
            String input = s.nextLine();

            if (input.equals("bye")){
                break;
            }

            System.out.println(horizontal_lines);
            System.out.println(input);
            System.out.println(horizontal_lines);
        }

    }

    // For greeting
    private static void printGreeting(){
        System.out.println(horizontal_lines);
        System.out.println("Hello! I'm Ben");
        System.out.println("What can I do for you?");
        System.out.println(horizontal_lines);
    }

    // goodbye
    private static void bidFarewell() {
        System.out.println(horizontal_lines);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_lines);
    }

}
