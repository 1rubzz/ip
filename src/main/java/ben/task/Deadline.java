package ben.task;

import ben.BenException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specified deadline
 */
public class Deadline extends Task {
    private LocalDate due;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a deadline task with the given date and description.
     *
     * @param date String due date of the task.
     * @param description Description of the task.
     * @throws BenException If the date format is invalid.
     */
    public Deadline(String date, String description) throws BenException {
        super(description);
        try {
            this.due = LocalDate.parse(date, INPUT_FORMAT);
        } catch (Exception e) {
            throw new BenException("Invalid date format. Please follow dd/MM/yyyy");
        }
    }

    /**
     * Returns the formatted status of the deadline task.
     *
     * @return String representation of the task status.
     */
    @Override
    public String returnStatus(){
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + due.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Converts the task into a string format suitable for file storage.
     *
     * @return String representation of the task for saving.
     */
    @Override
    public String fromTaskToString(){
        // Save to file data using input format and only use output format for printing.
        return "D | " + (super.isDone() ? 1 : 0) + " | " + super.getDescription() + " | " + due.format(INPUT_FORMAT);
    }
}
