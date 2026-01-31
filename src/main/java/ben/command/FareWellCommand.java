package ben.command;

import ben.BenException;
import ben.Storage;
import ben.TaskList;
import ben.Ui;

public class FareWellCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BenException {
        ui.bidFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
