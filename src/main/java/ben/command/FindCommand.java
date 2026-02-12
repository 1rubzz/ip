package ben.command;

import ben.Storage;
import ben.TaskList;
import ben.Ui;
import ben.task.Task;

/**
 * Represents a command that finds tasks whose descriptions contain
 * a specified keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a FindCommand with the given keyword.
     *
     * @param keyword Keyword used to search task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by displaying all tasks whose descriptions
     * contain the specified keyword.
     *
     * @param tasks Task list containing all current tasks.
     * @param ui User interface used to display messages.
     * @param storage Storage used to load and save tasks (not modified).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMessage("Here are the matching tasks in your list:");

        int count = 1;
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                ui.showMessage(count + "." + task.returnStatus());
                count++;
            }
        }

        ui.showLine();
    }
}
