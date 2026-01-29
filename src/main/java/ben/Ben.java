package ben;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents the main entry point of Ben the chatbot.
 *
 */
public class Ben {
    public static final String horizontal_lines = "----------------------------------------";
    public static int noOfTasks = 0;

    /**
     * Starts Ben the chatbot and initialises the required objects.
     *
     * @param args
     */
    public static void main(String[] args) {
        printGreeting();

        // Have a new scanner
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);

        Storage storage = new Storage("data/ben.txt");

        try {
            for (String line : storage.load()) {
                list.add(Task.fromStringToTask(line));
                noOfTasks++;
            }
        } catch (IOException | BenException e) {
            System.out.println("Failed to load saved tasks!");
        }

        recordTasks(scanner, list, storage);
        bidFarewell();
        System.out.println(horizontal_lines);
        scanner.close();
    }


    // echos prints messages to the screen
    private static void recordTasks(Scanner s, ArrayList<Task> list, Storage storage){
        while (true) {
            // scans the entire line
            String input = s.nextLine();

            try {
                processLine(input, list, storage);
            } catch (BenException e){
                if (e.getMessage().equals("EXIT")){
                    System.out.println(horizontal_lines);
                    break;
                }

                System.out.println(horizontal_lines);
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println(horizontal_lines);
            }
        }
    }

    private static void processLine(String input, ArrayList<Task> list, Storage storage) throws BenException {

        if (input.equals("bye")) {
            throw new BenException("EXIT"); // special signal
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
            return;
        }

        if (input.startsWith("mark")) {
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                throw new BenException("Please specify which task to mark.");
            }

            // cater to 0 indexed array list
            int index = Integer.parseInt(parts[1]) - 1;
            // get task
            Task curr = list.get(index);

            curr.markAsDone();
            saveTasks(storage, list);
            System.out.println(horizontal_lines);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(curr.returnStatus());
            System.out.println(horizontal_lines);
            return;
        }

        if (input.startsWith("unmark")){
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                throw new BenException("Please specify which task to unmark.");
            }

            int index = Integer.parseInt(parts[1]) - 1;
            Task curr = list.get(index);

            curr.unMarkAsDone();
            saveTasks(storage, list);
            System.out.println(horizontal_lines);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(curr.returnStatus());
            System.out.println(horizontal_lines);
            return;
        }

        // Added exception message
        if (input.startsWith("todo")) {
            String[] parts = input.split(" ", 2);
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new BenException("The description of a todo task cannot be empty.");
            }

            ToDo curr = new ToDo(parts[1]);
            list.add(curr);
            saveTasks(storage, list);
            noOfTasks++;

            System.out.println(horizontal_lines);
            System.out.println("Got it. I've added this task:");
            System.out.println(curr.returnStatus());
            System.out.println("Now you have " + noOfTasks + " tasks in the list.");
            System.out.println(horizontal_lines);
            return;
        }

        if (input.startsWith("deadline")){
            String[] parts1 = input.split(" ", 2);
            if (parts1.length < 2){
                throw new BenException("The description of a deadline task cannot be empty.");
            }

            String[] parts2 = parts1[1].split(" /by ");
            Deadline curr = new Deadline(parts2[1], parts2[0]);
            list.add(curr);
            saveTasks(storage, list);
            noOfTasks++;

            System.out.println(horizontal_lines);
            System.out.println("Got it. I've added this task:");
            System.out.println(curr.returnStatus());
            System.out.println("Now you have " + noOfTasks + " tasks in the list.");
            System.out.println(horizontal_lines);
            return;
        }

        if (input.startsWith("event")){
            String[] parts1 = input.split(" ", 2);
            String[] parts2 = parts1[1].split(" /from ", 2);
            String[] parts3 = parts2[1].split(" /to ");

            Event curr = new Event(parts3[0], parts3[1], parts2[0]);
            list.add(curr);
            saveTasks(storage, list);
            noOfTasks++;

            System.out.println(horizontal_lines);
            System.out.println("Got it. I've added this task:");
            System.out.println(curr.returnStatus());
            System.out.println("Now you have " + noOfTasks + " tasks in the list.");
            System.out.println(horizontal_lines);
            return;
        }

        if (input.startsWith("delete")){
            String[] parts = input.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            Task curr = list.get(index);
            list.remove(index);
            saveTasks(storage, list);
            noOfTasks--;

            System.out.println(horizontal_lines);
            System.out.println("Noted. I've removed this task:");
            System.out.println(curr.returnStatus());
            System.out.println("Now you have " + noOfTasks + " tasks in the list.");
            System.out.println(horizontal_lines);
            return;
        }

        throw new BenException("I'm sorry, but I don't know what that means :-(");
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

    private static void saveTasks(Storage storage, ArrayList<Task> list) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Task task : list) {
                lines.add(task.fromTaskToString());
            }
            storage.save(lines);
        } catch (IOException e) {
            System.out.println("Failed to save tasks to file");
        }
    }

}
