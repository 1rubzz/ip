package ben.command;

import ben.*;
import ben.task.Task;
import ben.task.ToDo;

public class CreateToDoCommand extends Command{

    private final String description;

    public CreateToDoCommand(String description) {
        this.description = description;
    }

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
