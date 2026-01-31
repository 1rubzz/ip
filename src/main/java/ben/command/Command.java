package ben.command;

import ben.*;
import ben.BenException;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BenException;

    public boolean isExit() {
        return false; // Should only be overridden by fareWellCommand
    }

    public static void saveTasks(Storage storage, TaskList list) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                lines.add(list.get(i).fromTaskToString());
            }
            storage.save(lines);
        } catch (IOException e) {
            System.out.println("Failed to save tasks to file");
        }
    }
}
