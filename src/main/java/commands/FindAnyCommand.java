package commands;

import storage.StorageFile;
import task.Task;
import task.TaskList;
import ui.TextUi;

import java.util.List;
import java.util.Set;

public class FindAnyCommand extends FindCommand{
    public static final String COMMAND_WORD = "findany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks that contain *all* of"
            + "the specific keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "\tParameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "\tExample: " + COMMAND_WORD + " book";

    private Set<String> keywords;

    public FindAnyCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    public FindAnyCommand() {

    }

    @Override
    public Set<String> getKeywords() {
        return keywords;
    }

    @Override
    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage){
        final List<Task> taskFound = taskList.getTaskContainAnyKeyword(getKeywords());
        ui.showFindTaskByKeywords(taskFound);
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }
}
