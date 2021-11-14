package com.weiwei.commands;

import com.weiwei.exception.BusinessException;
import com.weiwei.storage.StorageFile;
import com.weiwei.task.TaskList;
import com.weiwei.ui.TextUi;


public class FormatCommand extends Command{

    public static final String COMMAND_WORD = "format";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Formatting all tasks in the TaskList.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception{
        ui.showFormatFileMessage();
        taskList.clear();
        storage.saveTaskList(taskList.getAllTasks());
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }

    @Override
    public void setArguments(String arguments) throws BusinessException {

    }

    @Override
    public void validateArguments(String arguments) {

    }

}