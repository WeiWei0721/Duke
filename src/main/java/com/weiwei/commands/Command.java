package com.weiwei.commands;

import com.weiwei.exception.BusinessException;
import com.weiwei.storage.StorageFile;
import com.weiwei.task.TaskList;
import com.weiwei.ui.TextUi;

import static com.weiwei.common.Messages.MESSAGE_EMPTY_TASK_DESC;

public abstract class Command {

    private boolean isExit;

    public Command() {
    }

    public abstract void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception;

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
