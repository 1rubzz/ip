package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Task;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a command that deletes a task at the specified index.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        assert index >= 0 : "Index must be non-negative";
        this.index = index;
    }

    /**
     * Executes the command to remove a task from the task list.
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

        if (index >= tasks.size()) { // guard clause
            throw new BenException("Invalid task index.");
        }

        Task curr = tasks.get(index);
        tasks.remove(index);
        saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(curr.returnStatus());
        ui.showMessage("Now you have " + tasks.getNoOfTasks() + " tasks in the list.");
        ui.showLine();
    }

}
