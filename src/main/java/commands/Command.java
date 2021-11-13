package commands;

import exception.BusinessException;
import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

import static common.Messages.MESSAGE_EMPTY_TASK_DESC;

public abstract class Command {

    private boolean isExit;

    public Command() {
    }

    public abstract void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception;

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException();
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit(boolean exit) {
        this.isExit = exit;
    }

    public abstract String getMessageUsage();

    public abstract void setArguments(String arguments) throws BusinessException;

    public void validateArguments(String arguments) throws BusinessException {
        if (("").equals(arguments)) {
            throw new BusinessException(MESSAGE_EMPTY_TASK_DESC + "\n" + getMessageUsage());
        }
    }
}
