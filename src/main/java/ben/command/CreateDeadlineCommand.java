package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Deadline;
import ben.task.Task;

/**
 * Represents a command that creates a deadline task.
 */
public class CreateDeadlineCommand extends Command {

    private final String date;
    private final String description;

    /**
     * Creates a command that adds a deadline task.
     *
     * @param date        Date of the deadline.
     * @param description Description of the task.
     */
    public CreateDeadlineCommand(String date, String description) {
        assert date != null : "Date cannot be null";
        assert description != null : "Description cannot be null";
        assert !description.trim().isEmpty() : "Description cannot be empty";
        assert !date.trim().isEmpty() : "Date cannot be empty";

        this.date = date;
        this.description = description;
    }

    /**
     * Executes the command to create and add a deadline task.
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

        Task curr = new Deadline(this.date, this.description);
        tasks.add(curr);
        Command.saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(curr.returnStatus());
        ui.showMessage("Now you have " + tasks.getNoOfTasks() + " tasks in the list.");
        ui.showLine();
        return;
    }

}
