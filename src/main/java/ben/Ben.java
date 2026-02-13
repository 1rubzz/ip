package ben;

import java.io.IOException;

import ben.command.Command;

/**
 * Represents the main entry point of the Ben chatbot application.
 */
public class Ben {

    public static final String HORIZONTAL_LINES = "----------------------------------------";
    private int noOfTasks = 0;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Ben chatbot instance using the given file path.
     *
     * @param filePath File path used to load and save task data.
     */
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

    public Ben() {
        this("data/ben.txt"); // Use your actual default file path
    }

    /**
     * Runs the main program loop of the chatbot.
     */
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
     * Starts the Ben chatbot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Ben("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        Ui ui = new Ui();

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return ui.getOutput();
        } catch (BenException e) {
            return e.getMessage();
        }
    }

}
