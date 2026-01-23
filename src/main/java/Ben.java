import java.util.Scanner;
import java.util.ArrayList;

public class Ben {
    public static final String horizontal_lines = "----------------------------------------";

    public static void main(String[] args) {
        printGreeting();

        // Have a new scanner
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> toDo = new ArrayList<>(100);

        recordTasks(scanner, toDo);
        bidFarewell();
        System.out.println(horizontal_lines);
        scanner.close();
    }


    // echos prints messages to the screen
    private static void recordTasks(Scanner s, ArrayList<String> list){
        while (true) {
            // scans the entire line
            String input = s.nextLine();

            if (input.equals("bye")){
                System.out.println(horizontal_lines);
                break;
            }

            if (input.equals("list")) {
                System.out.println(horizontal_lines);

                for (int i = 0; i < list.size(); i++) {
                    String curr = list.get(i);
                    System.out.println((i + 1) + ". " + curr);
                }

                System.out.println(horizontal_lines);
                continue;
            }

            // else add input to list
            list.add(input);
            System.out.println(horizontal_lines);
            System.out.println("added: " + input);
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
