package ben.command;

import ben.*;
import ben.task.Deadline;
import ben.task.Task;

public class CreateDeadlineCommand extends Command {

    private final String date;
    private final String description;

    public CreateDeadlineCommand(String date, String description) {
        this.date = date;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
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
