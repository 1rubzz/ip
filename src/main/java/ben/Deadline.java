package ben;

/**
 * Represents a task with a specified deadline
 *
 */
public class Deadline extends Task{
    private String date;

    /**
     * Creates a deadline task with a specified deadline and description.
     *
     * @param date Due date of the task.
     * @param description Description of the task.
     */
    public Deadline(String date, String description){
        super(description);
        this.date = date;
    }

    @Override
    public String returnStatus(){
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + date + ")";
    }

    @Override
    public String fromTaskToString(){
        return "D | " + (super.isDone() ? 1 : 0) + " | " + super.getDescription() + " | " + date;
    }
}
