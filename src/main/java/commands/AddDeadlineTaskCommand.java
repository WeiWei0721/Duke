package commands;

import exception.BusinessException;
import storage.StorageFile;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.TextUi;
import util.DateUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class AddDeadlineTaskCommand extends Command {
    private static final Pattern ADD_DEADLINE_DATA_ARGS_FORMAT =
            Pattern.compile("(?<deadlineDescription>.*) ((\\/by)(?<deadlineDate>.*))");

    private Task task;

    public AddDeadlineTaskCommand(Task task) {
        this.task = task;
    }

    public static final String COMMAND_WORD = "deadline";

    // move to MESSAGE;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a DeadLine Task into the TaskList."
            + "DEADLINE_DATE to be specified in the following date format -> [d/MM/yyyy HHmm]\n"
            + "\tParameters: DEADLINE_DESCRIPTION /by DEADLINE_DATE \n"
            + "\tExample: "
            + COMMAND_WORD + " Read book /by 01/01/2021 2359";

    public AddDeadlineTaskCommand() {

    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws StorageFile.StorageOperationException {
        taskList.addTask(task);
        storage.save(task);
        ui.printAddTask(task, taskList.getSize());

    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }

    @Override
    public void setArguments(String arguments) throws BusinessException {
        validateArguments(arguments);
        final Matcher matcher = ADD_DEADLINE_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new BusinessException(MESSAGE_INVALID_COMMAND_FORMAT + "\n" + getMessageUsage());
        }

        String task = matcher.group("deadlineDescription").trim();
        String by = matcher.group("deadlineDate").trim();

        this.task = new Deadline(task, DateUtil.parseStringDateTimeFromText(by));
    }
}
