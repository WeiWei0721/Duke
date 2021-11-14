package commands;

import common.Messages;
import exception.BusinessException;
import storage.StorageFile;
import task.Task;
import task.TaskList;
import ui.TextUi;

public class DeleteTaskCommand extends Command {

    private int targetIndex;

    public DeleteTaskCommand(int tarageIndex) {
        this.targetIndex = tarageIndex;
    }

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "\tParameters: INDEX\n"
            + "\tExample: " + COMMAND_WORD + " 1";

    public DeleteTaskCommand() {

    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception {
        taskList.validate(targetIndex);
        Task deleTask = taskList.getTask(targetIndex - 1);
        taskList.delTask(targetIndex - 1);
        storage.saveTaskList(taskList.getAllTasks());
        ui.printDeleTask(deleTask, taskList.getSize());
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }

    @Override
    public void setArguments(String arguments) throws BusinessException {
        validateArguments(arguments);
        try {
            this.targetIndex = Integer.parseInt(arguments);
        } catch (Exception e) {
            throw new BusinessException(Messages.MESSAGE_INVALID_COMMAND_FORMAT + "\n" + getMessageUsage());
        }

    }
}
