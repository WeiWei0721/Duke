package commands;

import storage.StorageFile;
import task.*;
import task.TaskList;
import ui.TextUi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindCommand extends Command{

    public static final String COMMAND_WORD = CommandEnum.FIND.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks that contain any of"
            + "the specific keywords (case-sensitive) and displays them as a list with index numbers.\n"
            +"\tParameters: KEYWORD [MORE_KEYWORDS]...\n"
            +"\tExample: " + COMMAND_WORD + " book";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {this.keywords = keywords;}



    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage){
        final List<Task> taskFound = taskList.getTaskContainAnyKeyword(keywords);
        ui.showFindTaskByKeywords(taskFound);
    }
}


