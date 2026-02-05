package ben.command;

import ben.TaskList;
import ben.Ui;
import ben.Storage;
import ben.BenException;
import ben.task.Event;
import ben.task.Task;


/**
 * Represents a command that creates an event task.
 */
public class CreateEventCommand extends Command {

    private String startTime;
    private String endTime;
    private String description;

    /**
     * Creates a command that adds an event task.
     *
     * @param startTime Start time of the event.
     * @param endTime End time of the event.
     * @param description Description of the task.
     */
    public CreateEventCommand(String startTime, String endTime, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    /**
     * Executes the command to create and add an event task.
     *
     * @param tasks Task list containing all current tasks.
     * @param ui User interface used to display messages.
     * @param storage Storage used to load and save tasks.
     * @throws BenException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        Task curr = new Event(this.startTime, this.endTime, this.description);
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
