package ben;

import java.util.Scanner;

/**
 * Handles user interaction by printing messages and reading input.
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String horizontal_lines = "----------------------------------------";
    private StringBuilder output = new StringBuilder();

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        output.append(horizontal_lines).append("\n");
        output.append("Hello! I'm Ben\n");
        output.append("What can I do for you?\n");
        output.append(horizontal_lines).append("\n");
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
        output.append(horizontal_lines).append("\n");
    }

    /**
     * Displays the farewell message.
     */
    public void bidFarewell() {
        output.append(horizontal_lines).append("\n");
        output.append("Bye. Hope to see you again soon!\n");
        output.append(horizontal_lines).append("\n");
    }

    /**
     * Displays a message to the user.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        assert message != null : "Message should not be null";
        output.append(message).append("\n");
    }

    /**
     * Displays an error message.
     *
     * @param message Error message to be displayed.
     */
    public static void showError(String message) {
        // You may optionally implement this similarly if needed
    }

    /**
     * Returns the accumulated output.
     */
    public String getOutput() {
        return output.toString();
    }
}