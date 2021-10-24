package commands;

import storage.StorageFile;
import task.*;
import ui.TextUi;
import storage.*;

public class AddTodoTaskCommand extends Command{
    private Task task;

    public AddTodoTaskCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage){
        //taskList.addTaskAsTodo(task);
        taskList.addTask(task);
        storage.save(task);
        ui.printAddTask(task.getDescription(), taskList.getSize());

    }

}
