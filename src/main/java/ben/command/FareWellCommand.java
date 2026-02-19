package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;

/**
 * Represents a command that ends the program.
 */
public class FareWellCommand extends Command {

    /**
     * Executes the command to display a farewell message.
     *
     * @param tasks   Task list containing all current tasks.
     * @param ui      User interface used to display messages.
     * @param storage Storage used to load and save tasks.
     * @throws BenException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {

        assert ui != null : "Ui cannot be null";

        ui.bidFarewell();
    }

    /**
     * Indicates that this command causes the program to exit.
     *
     * @return True as the program should terminate after execution.
     */
    @Override
    public boolean isExit() {
        return true;
    }


}
