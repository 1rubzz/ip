package ben;

import ben.command.Command;
import ben.command.CreateDeadlineCommand;
import ben.command.CreateEventCommand;
import ben.command.CreateToDoCommand;
import ben.command.DeleteCommand;
import ben.command.FareWellCommand;
import ben.command.FindCommand;
import ben.command.ListCommand;
import ben.command.MarkCommand;
import ben.command.UnMarkCommand;

/**
 * Parses user input and creates the corresponding command.
 */
public class Parser {

    private static final String horizontal_lines = "----------------------------------------";

    /**
     * Parses the given input string and returns the corresponding command.
     *
     * @param fullCommand User input entered by the user.
     * @return Command corresponding to the user input.
     * @throws BenException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String fullCommand) throws BenException {
        if (fullCommand.equals("bye")) {
            return new FareWellCommand();
        }

        if (fullCommand.equals("list")) {
            return new ListCommand();
        }

        if (fullCommand.startsWith("mark")) {
            String[] parts = fullCommand.split(" ");
            if (parts.length != 2) {
                throw new BenException("Please specify which task to mark.");
            }

            // cater to 0 indexed array list
            int index = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(index);
        }

        if (fullCommand.startsWith("unmark")) {
            String[] parts = fullCommand.split(" ");
            if (parts.length != 2) {
                throw new BenException("Please specify which task to mark.");
            }

            // cater to 0 indexed array list
            int index = Integer.parseInt(parts[1]) - 1;
            return new UnMarkCommand(index);
        }

        if (fullCommand.startsWith("todo")) {
            String[] parts = fullCommand.split(" ", 2);
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new BenException("The description of a todo task cannot be empty.");
            }
            return new CreateToDoCommand(parts[1]);
        }

        if (fullCommand.startsWith("deadline")) {
            String[] parts1 = fullCommand.split(" ", 2);
            if (parts1.length < 2) {
                throw new BenException("The description of a deadline task cannot be empty.");
            }

            String[] parts2 = parts1[1].split(" /by ");
            return new CreateDeadlineCommand(parts2[1], parts2[0]);
        }

        if (fullCommand.startsWith("event")) {
            String[] parts1 = fullCommand.split(" ", 2);
            String[] parts2 = parts1[1].split(" /from ", 2);
            String[] parts3 = parts2[1].split(" /to ");

            return new CreateEventCommand(parts3[0], parts3[1], parts2[0]);
        }

        if (fullCommand.startsWith("delete")) {
            String[] parts = fullCommand.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;

            return new DeleteCommand(index);
        }

        // Level 9: Find
        // make sure that it is one object to find
        if (fullCommand.startsWith("find")) {
            String[] parts = fullCommand.split(" ", 2);

            if (parts.length < 2 || parts[1].isBlank()) {
                throw new BenException("Please provide a keyword to find.");
            }

            return new FindCommand(parts[1]);
        }



        throw new BenException("I'm sorry, but I don't know what that means :-(");

    }

}
