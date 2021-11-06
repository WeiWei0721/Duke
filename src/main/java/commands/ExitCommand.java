package commands;

import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class ExitCommand extends Command{

    public static final String COMMAND_WORD = CommandEnum.BYE.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage)  {
        ui.showGoodByeMessage();
        setExit(true);
    }
}
