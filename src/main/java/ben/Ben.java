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
    private boolean isExit; // Store the signal to exit

    /**
     * Creates a Ben chatbot instance using the given file path.
     *
     * @param filePath File path used to load and save task data.
     */
    public Ben(String filePath) {
        assert filePath != null : "File path should not be null";
        assert !filePath.trim().isEmpty() : "File path should not be empty";

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
        this("data/ben.txt"); // Use the actual default file path
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
                Ui.showError(e.getMessage());
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

    public String getResponse(String input) throws BenException {
        Ui ui = new Ui();

        Command c = Parser.parse(input);
        c.execute(tasks, ui, storage);

        isExit = c.isExit();

        return ui.getOutput();
    }

    /**
     * Checks if a FareWell command has been encountered.
     *
     * @return Boolean of exit status.
     */
    public boolean isExit() {
        return isExit;
    }
}
