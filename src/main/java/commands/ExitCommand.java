package commands;

import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage)  {
        ui.showGoodByeMessage();
        setExit(true);
    }
}
