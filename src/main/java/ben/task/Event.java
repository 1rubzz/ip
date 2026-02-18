package ben.task;

/**
 * Represents a task that occurs over a certain time period.
 *
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Creates an event task with a start time, end time and description.
     *
     * @param startTime   Start time of the event.
     * @param endTime     Endtime of the event.
     * @param description Description of the event.
     */
    public Event(String startTime, String endTime, String description) {
        assert startTime != null : "Event start time should not be null";
        assert endTime != null : "Event end time should not be null";
        assert description != null : "Event description should not be null";

        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the formatted status of the event task.
     *
     * @return String representation of the task status.
     */
    @Override
    public String returnStatus() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (from: "
                + this.startTime + " " + "to: " + this.endTime + ")";
    }

    /**
     * Converts the task into a string format suitable for file storage.
     *
     * @return String representation of the task for saving.
     */
    @Override
    public String fromTaskToString() {
        return "E | " + (super.isDone() ? 1 : 0) + " | " + super.getDescription() + " | " + startTime + " | " + endTime;
    }
}
