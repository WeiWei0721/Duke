package com.weiwei.commands;


import com.weiwei.exception.BusinessException;
import com.weiwei.storage.StorageFile;
import com.weiwei.task.TaskList;
import com.weiwei.ui.TextUi;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) {
        ui.showGoodByeMessage();
        setExit(true);
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
