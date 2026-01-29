package ben;

/**
 * Represents a basic task with a description and completion status.
 *
 */
public class Task {
    private Boolean isDone;
    private String description;

    /**
     * Creates a task with a specified description.
     *
     * @param description
     */
    public Task(String description){
        this.isDone = false;
        this.description = description;
    }

    /**
     * Marks this task as completed.
     *
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Marks this task and incomplete.
     *
     */
    public void unMarkAsDone(){
        this.isDone = false;
    }

    // getters
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the completion status of this task.
     *
     * @return Completion status.
     */
    public Boolean isDone(){
        return this.isDone;
    }

    /**
     * Returns status icon corresponding to this task's completion status.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns the String comprising of this task's status and description.
     *
     * @return String of task's status and description.
     */
    public String returnStatus(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Converts a line from the data file to a task object.
     *
     * @param line Line from data file.
     * @return Task that matches the line.
     * @throws BenException If corrupted data file is detected.
     */
    public static Task fromStringToTask(String line) throws BenException {
        String[] parts = line.split(" \\| ");

        switch (parts[0]){
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
     * Converts the task object to a string for saving to data file.
     *
     * @return String message corresponding to each type of tasks.
     */
    public String fromTaskToString() {
        return ""; // No useful implementation here
    }

}