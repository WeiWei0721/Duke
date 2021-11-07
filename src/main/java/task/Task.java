package task;

import java.util.Arrays;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    //public Task() { }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = null;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    /**
     * Retrieves a listing of every word in the name, in order.
     */
    public List<String> getWordsInTask(){
        return Arrays.asList(description.split("\\s+"));
    }


    public String getType() {
        return type;
    }


    public void markedAsDone() {
        this.isDone = true;

    }


    public String toString(){ // for toString in subclass
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * returns ture if both tasks have the same identify fields (description )
     */
    public boolean isSameTask(Task other){
        return other == this
                || (other != null
                    && other.getDescription().equals(this.getDescription())
//                    && other.getType().equals(this.getType())
//                    && other.getStatusIcon().equals(this.getStatusIcon())
//
        );
    }

}
