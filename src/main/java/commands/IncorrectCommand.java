package commands;

import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class IncorrectCommand extends Command {

    public final String errorMessage;

    public IncorrectCommand(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception{
        throw new Exception(errorMessage);

    }
}
