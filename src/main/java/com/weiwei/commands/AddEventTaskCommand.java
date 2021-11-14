package com.weiwei.commands;

import com.weiwei.exception.BusinessException;
import com.weiwei.storage.StorageFile;
import com.weiwei.task.Event;
import com.weiwei.task.Task;
import com.weiwei.task.TaskList;
import com.weiwei.ui.TextUi;
import com.weiwei.util.DateUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weiwei.common.Messages.MESSAGE_DATE_INVALID;
import static com.weiwei.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


public class AddEventTaskCommand extends Command {

    private static final Pattern ADD_EVENT_DATA_ARGS_FORMAT =
            Pattern.compile("(?<eventDescription>.*) (\\/at) (?<EventStartDate>.*) (\\-) (?<EventEndDate>.*)");
    private Task task;

    public AddEventTaskCommand(Task task) {
        this.task = task;
    }

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Event Task into the TaskList."
            + "EVENT_DATE to be specified in the following date format -> [d/MM/yyyy HH:mm] - [d/MM/yyyy HH:mm]\n"
            + "\tParameters: EVENT_DESCRIPTION /by EVENT_START_DATE - EVENT_END_DATE  \n"
            + "\tExample: "
            + COMMAND_WORD + " meeting /at 01/01/2021 14:00 - 01/01/2021 16:00";

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
