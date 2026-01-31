package ben.command;

import ben.*;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        Task curr = tasks.get(index);
        curr.markAsDone();
        Command.saveTasks(storage, tasks);

        ui.showLine();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(curr.returnStatus());
        ui.showLine();
        return;
    }

}
