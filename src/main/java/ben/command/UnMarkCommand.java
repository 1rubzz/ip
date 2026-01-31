package ben.command;

import ben.*;
import ben.task.Task;

public class UnMarkCommand extends Command{

    private final int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        Task curr = tasks.get(index);
        curr.unMarkAsDone();
        Command.saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(curr.returnStatus());
        ui.showMessage("Now you have " + tasks.getNoOfTasks() + " tasks in the list.");
        ui.showLine();
        return;
    }

}
