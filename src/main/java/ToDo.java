public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public String returnStatus(){
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
