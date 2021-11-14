package commands;

import common.Messages;
import exception.BusinessException;
import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

import java.util.regex.Pattern;

public class DoneTaskCommand extends Command {

    private int taskNo;

    public DoneTaskCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public static final String COMMAND_WORD ="done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task as done identified by the index number used in the last task listing.\n"
            + "\tParameters: INDEX\n"
            + "\tExample: " + COMMAND_WORD + " 1";

    public DoneTaskCommand() {

    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception {
        taskList.validate(taskNo);
        taskList.markedAsDone(this.taskNo);
        storage.saveTaskList(taskList.getAllTasks());
        ui.showMarkDoneTask(taskList.getTask(taskNo));
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }

    @Override
    public void setArguments(String arguments) throws BusinessException {
        validateArguments(arguments);
        try {
            int taskNo = Integer.parseInt(arguments);
        } catch (Exception e) {
            throw new BusinessException(Messages.MESSAGE_INVALID_COMMAND_FORMAT + "\n" + getMessageUsage());
        }

        this.taskNo = taskNo - 1;
    }

}
