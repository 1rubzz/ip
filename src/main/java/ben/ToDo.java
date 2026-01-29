package ben;

/**
 * Represents a simple task without any associated date or time.
 *
 */
public class ToDo extends Task{

    /**
     * Creates a to-do task with a specified description.
     *
     * @param description Description of the to-do task
     */
    public ToDo(String description){
        super(description);
    }

    @Override
    public String returnStatus(){
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    public String fromTaskToString(){
        return "T | " + (super.isDone() ? 1 : 0) + " | " + super.getDescription();
    }
}
