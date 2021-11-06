package commands;

import storage.StorageFile;
import task.*;
import ui.TextUi;
import storage.*;


public class AddTodoTaskCommand extends Command{
    private Task task;

    public AddTodoTaskCommand(Task task){
        this.task = task;
    }

    public static final String COMMAND_WORD = CommandEnum.TODO.toString().toLowerCase();

    // move to MESSAGE;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Todo Task into the TaskList.\n"
            + "\tParameters: TODO_DESCRIPTION\n"
            + "\tExample: "
            + COMMAND_WORD + " Read book";


    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws StorageFile.StorageOperationException {
        //taskList.addTaskAsTodo(task);
        taskList.addTask(task);
        storage.save(task);//getLatesAddedTask?
        ui.printAddTask(task, taskList.getSize());

    }

}
