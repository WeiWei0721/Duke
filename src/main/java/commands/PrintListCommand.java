package commands;

import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class PrintListCommand extends Command{

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage){
        taskList.printList();
    }

}
