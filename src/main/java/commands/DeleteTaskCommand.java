package commands;

import storage.StorageFile;
import task.*;
import ui.TextUi;

public class DeleteTaskCommand extends Command{

    private int tarageIndex;

    public DeleteTaskCommand(int tarageIndex){
        this.tarageIndex = tarageIndex;
    }

    public static final String COMMAND_WORD = CommandEnum.DELETE.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "\tParameters: INDEX\n"
            + "\tExample: " + COMMAND_WORD + " 1";

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception {
        Task deleTask = taskList.getTask(tarageIndex-1);
        taskList.delTask(tarageIndex-1);
        storage.saveTaskList(taskList.getAllTasks());
        ui.printDeleTask(deleTask, taskList.getSize());
    }
}
