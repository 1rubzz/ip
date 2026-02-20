package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Task;

/**
 * Represents a command that unmarks a task as done.
 */
public class UnMarkCommand extends Command {

    private final int index;

    /**
     * Creates a command that unmarks a task at the specified index.
     *
     * @param index Index of the task to be unmarked.
     */
    public UnMarkCommand(int index) {
        assert index >= 0 : "Index must be non-negative";
        this.index = index;
    }

    /**
     * Executes the command to mark a task as not completed.
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
        curr.unMarkAsDone();
        Command.saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Got it. I've unmarked task " + (index + 1));
        ui.showMessage(curr.returnStatus());
        ui.showLine();
    }

}
