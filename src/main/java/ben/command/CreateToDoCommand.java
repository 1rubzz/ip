package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Task;
import ben.task.ToDo;

/**
 * Represents a command that creates a todo task.
 */
public class CreateToDoCommand extends Command {

    private final String description;

    /**
     * Creates a command that adds a todo task.
     *
     * @param description Description of the task.
     */
    public CreateToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to create and add a todo task.
     *
     * @param tasks   Task list containing all current tasks.
     * @param ui      User interface used to display messages.
     * @param storage Storage used to load and save tasks.
     * @throws BenException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        Task curr = new ToDo(description);
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
