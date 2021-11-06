package commands;

import storage.StorageFile;
import task.*;
import ui.TextUi;

import java.util.Locale;

public class AddDeadlineTaskCommand extends Command{
    private final Task task;

    public AddDeadlineTaskCommand(Task task){
        this.task = task;
    }

    public static final String COMMAND_WORD = CommandEnum.DEADLINE.toString().toLowerCase();

    // move to MESSAGE;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a DeadLine Task into the TaskList."
            + "DEADLINE_DATE to be specified in the following date format -> [d/MM/yyyy HHmm]\n"
            + "\tParameters: DEADLINE_DESCRIPTION /by DEADLINE_DATE \n"
            + "\tExample: "
            + COMMAND_WORD + " Read book /by 01/01/2021 2359";



    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws StorageFile.StorageOperationException {
        taskList.addTask(task);
        storage.save(task);
        ui.printAddTask(task, taskList.getSize());

    }
}
