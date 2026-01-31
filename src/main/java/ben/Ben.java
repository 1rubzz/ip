package ben;

import ben.command.Command;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents the main entry point of Ben the chatbot.
 *
 */
public class Ben {

    public static final String horizontal_lines = "----------------------------------------";
    private int noOfTasks = 0;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ben(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException | BenException e) {
            this.tasks = new TaskList();
        }
        noOfTasks = tasks.getNoOfTasks();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand); // parsed command
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BenException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        new Ben("data/tasks.txt").run();
    }

}
