package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.Task;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        ui.showLine();
        ui.showMessage("Here are the tasks in your list");

        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            ui.showMessage((i + 1) + "." + curr.returnStatus());
        }

        ui.showLine();
        return;
    }

}
