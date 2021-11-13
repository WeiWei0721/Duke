package commands;

import exception.BusinessException;
import storage.StorageFile;
import task.Event;
import task.Task;
import task.TaskList;
import ui.TextUi;
import util.DateUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Messages.MESSAGE_DATE_INVALID;
import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class AddEventTaskCommand extends Command {

    private static final Pattern ADD_EVENT_DATA_ARGS_FORMAT =
            Pattern.compile("(?<eventDescription>.*) (\\/at) (?<EventStartDate>.*) (\\-) (?<EventEndDate>.*)");
    private Task task;

    public AddEventTaskCommand(Task task) {
        this.task = task;
    }

    public static final String COMMAND_WORD = "event";

    // move to MESSAGE;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Event Task into the TaskList."
            + "EVENT_DATE to be specified in the following date format -> [d/MM/yyyy HHmm] - [d/MM/yyyy HHmm]\n"
            + "\tParameters: EVENT_DESCRIPTION /by EVENT_START_DATE - EVENT_END_DATE  \n"
            + "\tExample: "
            + COMMAND_WORD + " meeting /at 01/01/2021 1400 - 01/01/2021 1600";

    public AddEventTaskCommand() {

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
        final Matcher matcher = ADD_EVENT_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new BusinessException(MESSAGE_INVALID_COMMAND_FORMAT + "\n"+ getMessageUsage());
        }

        String desc = matcher.group("eventDescription").trim();
        String start = matcher.group("EventStartDate").trim();
        String end = matcher.group("EventEndDate").trim();

        this.task = new Event(desc, DateUtil.parseStringDateTimeFromText(start), DateUtil.parseStringDateTimeFromText(end));
        if (!((Event) task).getStart().isBefore(((Event) task).getEnd())){
            throw new BusinessException(MESSAGE_DATE_INVALID + "\n" + getMessageUsage());
        }
    }
}
