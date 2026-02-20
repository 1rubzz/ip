package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Event;
import ben.task.Task;


/**
 * Represents a command that creates an event task.
 */
public class CreateEventCommand extends Command {

    private final String startTime;
    private final String endTime;
    private final String description;

    /**
     * Creates a command that adds an event task.
     *
     * @param startTime   Start time of the event.
     * @param endTime     End time of the event.
     * @param description Description of the task.
     */
    public CreateEventCommand(String startTime, String endTime, String description) {

        assert startTime != null : "Start time cannot be null";
        assert endTime != null : "End time cannot be null";
        assert description != null : "Description cannot be null";

        assert !startTime.trim().isEmpty() : "Start time cannot be empty";
        assert !endTime.trim().isEmpty() : "End time cannot be empty";
        assert !description.trim().isEmpty() : "Description cannot be empty";

        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    /**
     * Executes the command to create and add an event task.
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

        Task curr = new Event(this.startTime, this.endTime, this.description);
        tasks.add(curr);
        Command.saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(curr.returnStatus());
        ui.showMessage("Now you have " + tasks.getNoOfTasks() + " tasks in the list.");
        ui.showLine();
    }

}
