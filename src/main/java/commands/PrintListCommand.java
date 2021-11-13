package commands;

import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class PrintListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the tasks in the task list as a list with index numbers.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) {
        taskList.printList();
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }

    @Override
    public void setArguments(String arguments) {

    }

    @Override
    public void validateArguments(String arguments) {

    }

}
