package com.weiwei.commands;


import com.weiwei.exception.BusinessException;
import com.weiwei.storage.StorageFile;
import com.weiwei.task.Task;
import com.weiwei.task.TaskList;
import com.weiwei.ui.TextUi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weiwei.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindCommand extends Command {
    private static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks that contain *any* of "
            + "the specific keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "\tParameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "\tExample: " + COMMAND_WORD + " book";

    private Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    public FindCommand() {
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) {
        final List<Task> taskFound = taskList.getTaskContainAllKeyword(getKeywords());
        ui.showFindTaskByKeywords(taskFound);
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }

    @Override
    public void setArguments(String arguments) throws BusinessException {
        validateArguments(arguments);
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(arguments.toLowerCase().trim());
        if (!matcher.matches()) {
            throw new BusinessException(MESSAGE_INVALID_COMMAND_FORMAT + "\n" + getMessageUsage());
        }

        //keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        setKeywords(keywordSet);
    }
}


