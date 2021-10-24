package commands;


import task.TaskList;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public final String feedbackToUser;

    //private final List<? extends TaskList> taskListResult;

    public CommandResult(String feedbackToUser){
        this.feedbackToUser = feedbackToUser;
    }
}
