package commands;

import common.Messages;
import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class DoneTaskCommand extends Command {
    private final int taskNo;

    public DoneTaskCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public static final String COMMAND_WORD = CommandEnum.DONE.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task as done identified by the index number used in the last task listing.\n"
            + "\tParameters: INDEX\n"
            + "\tExample: " + COMMAND_WORD + " 1";

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws Exception {
        taskList.validate(taskNo);
        taskList.markedAsDone(this.taskNo);
        storage.saveTaskList(taskList.getAllTasks());
        ui.showMarkDoneTask(taskList.getTask(taskNo));
    }

}
