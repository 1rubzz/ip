package ben.task;

/**
 * Represents a simple task without any associated date or time.
 *
 */
public class ToDo extends Task {

    /**
     * Creates a to-do task with a specified description.
     *
     * @param description Description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the formatted status of the to-do task.
     *
     * @return String representation of the task status.
     */
    @Override
    public String returnStatus() {
        String base = "[T]" + "[" + super.getStatusIcon() + "] " + super.getDescription();
        if (tag != null) {
            base += " #" + tag;
        }
        return base;
    }

    /**
     * Converts the task into a string format suitable for file storage.
     *
     * @return String representation of the task for saving.
     */
    @Override
    public String fromTaskToString() {
        String base = "T | " + (super.isDone() ? 1 : 0) + " | " + super.getDescription();
        if (tag != null) {
            base += " | " + tag;
        }
        return base;
    }
}
