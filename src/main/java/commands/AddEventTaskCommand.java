package commands;

import storage.StorageFile;
import task.*;
import ui.TextUi;

public class AddEventTaskCommand extends Command{
    private Task task;

    public AddEventTaskCommand(Task task){ this.task = task; }

    public static final String COMMAND_WORD = CommandEnum.EVENT.toString().toLowerCase();

    // move to MESSAGE;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Event Task into the TaskList."
            + "EVENT_DATE to be specified in the following date format -> [d/MM/yyyy HHmm] - [d/MM/yyyy HHmm]\n"
            + "\tParameters: EVENT_DESCRIPTION /by EVENT_START_DATE - EVENT_END_DATE  \n"
            + "\tExample: "
            + COMMAND_WORD + " meeting /at 01/01/2021 1400 - 01/01/2021 1600";

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws StorageFile.StorageOperationException {
        taskList.addTask(task);
        storage.save(task);
        ui.printAddTask(task, taskList.getSize());

    }
}
