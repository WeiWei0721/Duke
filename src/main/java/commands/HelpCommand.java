package commands;

import exception.BusinessException;
import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

import java.util.ArrayList;
import java.util.List;


public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) {
        List<String> commandList = new ArrayList<>();
        commandList.add(AddTodoTaskCommand.MESSAGE_USAGE);
        commandList.add(AddDeadlineTaskCommand.MESSAGE_USAGE);
        commandList.add(AddEventTaskCommand.MESSAGE_USAGE);
        commandList.add(DoneTaskCommand.MESSAGE_USAGE);
        commandList.add(DeleteTaskCommand.MESSAGE_USAGE);
        commandList.add(FindCommand.MESSAGE_USAGE);
        commandList.add(FormatCommand.MESSAGE_USAGE);
        commandList.add(PrintListCommand.MESSAGE_USAGE);
        commandList.add(HelpCommand.MESSAGE_USAGE);
        commandList.add(ExitCommand.MESSAGE_USAGE);

        ui.showCommands(commandList);

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
