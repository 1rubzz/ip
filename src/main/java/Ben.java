import java.util.Scanner;
import java.util.ArrayList;

public class Ben {
    public static final String horizontal_lines = "----------------------------------------";
    public static int noOfTasks = 0;

    public static void main(String[] args) {
        printGreeting();

        // Have a new scanner
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);

        recordTasks(scanner, list);
        bidFarewell();
        System.out.println(horizontal_lines);
        scanner.close();
    }


    // echos prints messages to the screen
    private static void recordTasks(Scanner s, ArrayList<Task> list){
        while (true) {
            // scans the entire line
            String input = s.nextLine();

            if (input.equals("bye")){
                System.out.println(horizontal_lines);
                break;
            }

            // prints the list
            if (input.equals("list")) {
                System.out.println(horizontal_lines);
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < list.size(); i++) {
                    Task curr = list.get(i);
                    System.out.println((i + 1) + "." + curr.returnStatus());
                }

                System.out.println(horizontal_lines);
                continue;
            }

            if (input.startsWith("mark")){
                String[] parts = input.split(" ");
                // cater to 0 indexed array list
                int index = Integer.parseInt(parts[1]) - 1;
                // get task
                Task curr = list.get(index);

                curr.markAsDone();
                System.out.println(horizontal_lines);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(curr.returnStatus());
                System.out.println(horizontal_lines);
                continue;
            }

            if (input.startsWith("unmark")){
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                Task curr = list.get(index);

                curr.unMarkAsDone();
                System.out.println(horizontal_lines);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(curr.returnStatus());
                System.out.println(horizontal_lines);
                continue;
            }

            if (input.startsWith("todo")){
                String[] parts = input.split(" ", 2);
                ToDo curr = new ToDo(parts[1]);
                list.add(curr);
                noOfTasks++;

                System.out.println(horizontal_lines);
                System.out.println("Got it. I've added this task:");
                System.out.println(curr.returnStatus());
                System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                System.out.println(horizontal_lines);
                continue;
            }

            if (input.startsWith("deadline")){
                String[] parts1 = input.split(" ", 2);
                String[] parts2 = parts1[1].split(" /by ");
                Deadline curr = new Deadline(parts2[1], parts2[0]);
                list.add(curr);
                noOfTasks++;

                System.out.println(horizontal_lines);
                System.out.println("Got it. I've added this task:");
                System.out.println(curr.returnStatus());
                System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                System.out.println(horizontal_lines);
                continue;
            }

            if (input.startsWith("event")){
                String[] parts1 = input.split(" ", 2);
                String[] parts2 = parts1[1].split(" /from ", 2);
                String[] parts3 = parts2[1].split(" /to ");

                Event curr = new Event(parts3[0], parts3[1], parts2[0]);
                list.add(curr);
                noOfTasks++;

                System.out.println(horizontal_lines);
                System.out.println("Got it. I've added this task:");
                System.out.println(curr.returnStatus());
                System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                System.out.println(horizontal_lines);
                continue;
            }

            // else add input to list
            list.add(new Task(input));
            noOfTasks++;
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
