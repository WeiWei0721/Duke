package com.weiwei.commands;

import com.weiwei.exception.BusinessException;
import com.weiwei.storage.StorageFile;
import com.weiwei.task.Task;
import com.weiwei.task.TaskList;
import com.weiwei.task.Todo;
import com.weiwei.ui.TextUi;

public class AddTodoTaskCommand extends Command {
    private Task task;

    public AddTodoTaskCommand(Task task) {
        this.task = task;
    }

    public static final String COMMAND_WORD = "todo";

    // move to MESSAGE;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Todo Task into the TaskList.\n"
            + "\tParameters: TODO_DESCRIPTION\n"
            + "\tExample: "
            + COMMAND_WORD + " Read book";

    public AddTodoTaskCommand() {

    }

    @Override
    public void execute(TaskList taskList, TextUi ui, StorageFile storage) throws StorageFile.StorageOperationException {
        //taskList.addTaskAsTodo(task);
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
        new AddTodoTaskCommand(this.task = new Todo(arguments));
    }

}
