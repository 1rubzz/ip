package ben.command;

// Do not use any wildcards for imports

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Task;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a command that marks a task at the specified index as done.
     *
     * @param index Index of the task to be marked.
     */
    public MarkCommand(int index) {
        assert index >= 0 : "Index must be non-negative";
        this.index = index;
    }

    /**
     * Executes the command to mark a task as completed.
     *
     * @param tasks   Task list containing all current tasks.
     * @param ui      User interface used to display messages.
     * @param storage Storage used to load and save tasks.
     * @throws BenException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {

        assert tasks != null : ASSERT_TASKLIST_NULL;
        assert ui != null : ASSERT_UI_NULL;
        assert storage != null : ASSERT_STORAGE_NULL;

        if (index >= tasks.size()) { // Guard clause
            throw new BenException("Invalid task index.");
        }

        Task curr = tasks.get(index);
        curr.markAsDone();
        Command.saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Got it. I've marked task " + (index + 1) + " as done:");
        ui.showMessage(curr.returnStatus());
        ui.showLine();
    }

}
