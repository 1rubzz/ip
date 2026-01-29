package ben;

/**
 * Represents a task that occurs over a certain time period.
 *
 */
public class Event extends Task{
    private String startTime;
    private String endTime;

    /**
     * Creates an event task with a start time, end time and description.
     *
     * @param startTime Start time of the event.
     * @param endTime Endtime of the event.
     * @param description Description of the event.
     */
    public Event(String startTime, String endTime, String description){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String returnStatus(){
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String fromTaskToString(){
        return "E | " + (super.isDone() ? 1 : 0) + " | " + super.getDescription() + " | " + startTime + " | " + endTime;
    }
}
