package ben.command;

import ben.*;
import ben.task.Event;
import ben.task.Task;

public class CreateEventCommand extends Command {

    private String startTime;
    private String endTime;
    private String description;

    public CreateEventCommand(String startTime, String endTime, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

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
