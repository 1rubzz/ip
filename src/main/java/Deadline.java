public class Deadline extends Task{
    private String date;

    public Deadline(String date, String description){
        super(description);
        this.date = date;
    }

    @Override
    public String returnStatus(){
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + date + ")";
    }
}
