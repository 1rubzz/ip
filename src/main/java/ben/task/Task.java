package ben.task;

import ben.BenException;

/**
 * Represents a basic task with a description and completion status.
 */
public class Task {
    private Boolean isDone;
    private String description;

    /**
     * Creates a task with the specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null : "Description should not be null";
        assert !description.trim().isEmpty() : "Description should not be empty";

        this.isDone = false;
        this.description = description;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unMarkAsDone() {
        this.isDone = false;
    }

    // getters

    /**
     * Returns the description of this task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether this task is completed.
     *
     * @return Completion status of the task.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the status icon of this task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns the formatted status of this task.
     *
     * @return String representation of the task status.
     */
    public String returnStatus() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Converts a line from the data file into a task object.
     *
     * @param line Line from the data file.
     * @return Task that matches the given line.
     * @throws BenException If corrupted data is detected.
     */
    public static Task fromStringToTask(String line) throws BenException {
        String[] parts = line.split(" \\| ");

        switch (parts[0]) {
        case "T": {
            Task todo = new ToDo(parts[2]);
            if (parts[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        }

        case "D": {
            Task deadline = new Deadline(parts[3], parts[2]);
            if (parts[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        }

        case "E": {
            Task event = new Event(parts[3], parts[4], parts[2]);
            if (parts[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        }

        default:
            throw new BenException("Corrupted file data!");
        }
    }

    /**
     * Converts this task into a string format suitable for file storage.
     *
     * @return String representation of the task for saving.
     */
    public String fromTaskToString() {
        return ""; // No useful implementation here
    }

}
