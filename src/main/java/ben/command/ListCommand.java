package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Task;

/**
 * Represents a command that lists all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display all tasks in the task list.
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

        if (tasks.size() == 0) { // Guard clause
            ui.showLine();
            ui.showMessage("Your task list is empty.");
            ui.showLine();
            return;
        }

        ui.showLine();
        ui.showMessage("Here are the tasks in your list");

        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            ui.showMessage((i + 1) + ". " + curr.returnStatus());
        }

        ui.showLine();
    }

}
