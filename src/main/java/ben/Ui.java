package ben;

import java.util.Scanner;

/**
 * Prints messages.
 * Read input.
 *
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String horizontal_lines = "----------------------------------------";

    public void showWelcome() {
        System.out.println(horizontal_lines);
        System.out.println("Hello! I'm Ben");
        System.out.println("What can I do for you?");
        System.out.println(horizontal_lines);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(horizontal_lines);
    }

    public void bidFarewell() {
        System.out.println(horizontal_lines);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_lines);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public static void showError(String message) {
    }
}
