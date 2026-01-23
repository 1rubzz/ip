public class Task {
    private Boolean isDone;
    private String description;

    public Task(String description){
        this.isDone = false;
        this.description = description;
    }

    // mark task as done
    public void markAsDone(){
        this.isDone = true;
    }

    // unmark a task
    public void unMarkAsDone(){
        this.isDone = false;
    }

    // getters
    public String getDescription(){
        return this.description;
    }

    public Boolean isDone(){
        return this.isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String returnStatus(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}