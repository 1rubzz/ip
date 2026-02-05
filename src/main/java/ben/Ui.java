package ben;

import java.util.Scanner;

/**
 * Handles user interaction by printing messages and reading input.
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String horizontal_lines = "----------------------------------------";

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println(horizontal_lines);
        System.out.println("Hello! I'm Ben");
        System.out.println("What can I do for you?");
        System.out.println(horizontal_lines);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return User input string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println(horizontal_lines);
    }

    /**
     * Displays the farewell message.
     */
    public void bidFarewell() {
        System.out.println(horizontal_lines);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_lines);
    }

    /**
     * Displays a message to the user.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message.
     *
     * @param message Error message to be displayed.
     */
    public static void showError(String message) {
    }
}
