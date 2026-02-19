package ben.command;

import java.io.IOException;
import java.util.ArrayList;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;


/**
 * Represents an executable user command.
 * Defines an action that can be performed by the application.
 */
public abstract class Command {

    // central assertion message, reduce magic strings
    protected static final String ASSERT_TASKLIST_NULL =
            "TaskList cannot be null";

    protected static final String ASSERT_UI_NULL =
            "Ui cannot be null";

    protected static final String ASSERT_STORAGE_NULL =
            "Storage cannot be null";

    /**
     * Executes the command.
     *
     * @param tasks   Task list containing all current tasks.
     * @param ui      User interface used to display messages.
     * @param storage Storage used to load and save tasks.
     * @throws BenException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BenException;

    /**
     * Indicates whether the program should exit after executing this command.
     *
     * @return True if the program should terminate, false otherwise.
     */
    public boolean isExit() {
        return false; // Should only be overridden by fareWellCommand
    }

    /**
     * Saves the current list of tasks to the data file.
     *
     * @param storage Storage responsible for writing data to the file.
     * @param list    Task list containing the tasks to be saved.
     */
    public static void saveTasks(Storage storage, TaskList list) throws BenException {

        assert storage != null : "Storage should not be null";
        assert list != null : "TaskList should not be null";

        try {
            ArrayList<String> lines = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                lines.add(list.get(i).fromTaskToString());
            }
            storage.save(lines);
        } catch (IOException e) {
            // Throw error rather than printing error message
            throw new BenException("Failed to save tasks to file");
        }
    }
}
