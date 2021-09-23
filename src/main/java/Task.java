public class Task {
    protected String description;
    protected boolean isDone;

    public Task(){

    }


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return description;
    }

//    public void description(String description){
//        this.description = description;
//    }

    public void markedAsDone(){
        this.isDone = true;

    }

}
