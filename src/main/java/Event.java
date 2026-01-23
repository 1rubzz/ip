public class Event extends Task{
    private String startTime;
    private String endTime;

    public Event(String startTime, String endTime, String description){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String returnStatus(){
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
