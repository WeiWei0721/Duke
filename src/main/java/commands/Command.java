package commands;

import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public abstract class Command {

    private boolean isExit;

    public Command(){ }

    public abstract void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception;

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute(){
        throw new UnsupportedOperationException();
    }

    public boolean isExit(){ return this.isExit;}

    public void setExit(boolean exit){ this.isExit = exit;}
}
