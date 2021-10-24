package commands;

import storage.StorageFile;
import task.*;
import ui.TextUi;

public class DeleteTaskCommand extends Command{

    private int tarageIndex;

    public DeleteTaskCommand(int tarageIndex){
        this.tarageIndex = tarageIndex;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage)  {
        taskList.delTask(tarageIndex);
    }
}
