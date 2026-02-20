package ben.task;

import ben.BenException;

/**
 * Represents a basic task with a description and completion status.
 */
public abstract class Task {
    private boolean isDone;
    private final String description;
    protected String tag;

    /**
     * Creates a task with the specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null : "Description should not be null";
        assert !description.trim().isEmpty() : "Description should not be empty";

        this.description = description;
        this.tag = null; // tag will be null by default
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
        String base = "[" + this.getStatusIcon() + "] " + this.getDescription();
        if (tag != null) { // show the necessary tag
            base += " #" + tag;
        }
        return base;
    }

    /**
     * Sets a tag for the task.
     *
     * @param tag Tag to be attached.
     */
    public void setTag(String tag) {
        assert tag != null : "Tag should not be null";
        this.tag = tag;
    }

    /**
     * Removes a tag from the task.
     *
     * @throws BenException If there is no tag to remove.
     */
    public void removeTag() throws BenException {
        if (tag == null) {
            throw new BenException("This task has no tag to remove.");
        }
        tag = null;
    }

    /**
     * Returns the tag of this task.
     *
     * @return Tag string, or null if no tag.
     */
    public String getTag() {
        return tag;
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

            // Tag exists if length > 3
            if (parts.length > 3) {
                todo.setTag(parts[3]);
            }

            return todo;
        }

        case "D": {
            Task deadline = new Deadline(parts[3], parts[2]);
            if (parts[1].equals("1")) {
                deadline.markAsDone();
            }

            // Tag exists if length > 4
            if (parts.length > 4) {
                deadline.setTag(parts[4]);
            }

            return deadline;
        }

        case "E": {
            Task event = new Event(parts[3], parts[4], parts[2]);
            if (parts[1].equals("1")) {
                event.markAsDone();
            }

            // Tag exists if length > 5
            if (parts.length > 5) {
                event.setTag(parts[5]);
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
    public abstract String fromTaskToString();

}
