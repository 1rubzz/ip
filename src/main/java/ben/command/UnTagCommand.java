package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Task;

public class UnTagCommand extends Command {

    private final int index;

    public UnTagCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        assert tasks != null : ASSERT_TASKLIST_NULL;
        assert ui != null : ASSERT_UI_NULL;
        assert storage != null : ASSERT_STORAGE_NULL;

        if (index < 0 || index >= tasks.size()) {
            throw new BenException("Task number is out of range.");
        }

        Task curr = tasks.get(index);
        curr.removeTag();
        Command.saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Nice! I have untagged the task " + index);
        ui.showMessage(curr.returnStatus());
        ui.showLine();
    }
}