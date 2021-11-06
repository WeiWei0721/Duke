package exception;


public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(int taskNo){
        super("Task " + taskNo + " does not exist.");
    }
}
