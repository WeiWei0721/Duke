package commands;

import storage.StorageFile;
import task.*;
import ui.TextUi;

public class AddDeadlineTaskCommand extends Command{
    private Task task;

    public AddDeadlineTaskCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage){
        taskList.addTask(task);
        storage.save(task);
        ui.printAddTask(task.getDescription(), taskList.getSize());

    }
}
