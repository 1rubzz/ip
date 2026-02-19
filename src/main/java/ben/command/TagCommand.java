package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Task;

public class TagCommand extends Command {

    private final int index;
    private final String tag;

    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        Task curr = tasks.get(index);
        curr.setTag(tag);
        Command.saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Nice! I have tagged the task with #" + tag);
        ui.showMessage(curr.returnStatus());
        ui.showLine();
        return;
    }
}