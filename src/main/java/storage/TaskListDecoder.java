package storage;

import exception.*;
import task.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Decodes the storage data file into an {@code TaskList} object.
 */

public class TaskListDecoder {
    /**
     * Decodes {@code encodedTaskList} into an {@code TaskList} containing the decoded tasks.
     *
     * @throws IllegalValueException if any of the fields in any encoded task string is invalid.
     * @throws StorageOperationException if the {@code encodedTaskList} is in an invalid format.
     */
    public static TaskList decodeTaskList(List<String> encodedTaskList)
        throws IllegalValueException, StorageFile.StorageOperationException{
        final TaskList decodedTasks = new TaskList();
        for(String encodedTask : encodedTaskList){
            decodedTasks.addTask(decodeTaskListFromString(encodedTask));
        }

        return decodedTasks;

    }


    public static final Pattern TASK_DATA_ARGS_FORMAT =
            Pattern.compile("((^[DE]) \\| ([0-1]) \\| (.*\\|) (.*))|((^[T]) \\| ([0-1]) \\| (.*))");


    private static Task decodeTaskListFromString(String encodedTask)
        throws IllegalValueException, StorageFile.StorageOperationException {
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(encodedTask);
        if(!matcher.matches()){
            throw new StorageFile.InvalidStorageFilePathException("Encoded task is invalid format. Unable to decode.");
        }

        Task newTask = null;
        String[] data = encodedTask.split("[|]");
        String taskType = data[0].trim();
        String taskStatus = data[1].trim();
        String taskDescription = data[2].trim();

        if(taskType.equals(TaskListEnum.T.toString())){
            //Todo Task.
            newTask = new Todo(taskDescription);
            if(taskStatus.equals("1")){
                newTask.markedAsDone();
            }
        }else if(taskType.equals(TaskListEnum.D.toString())){
            //Deadline Task.
            String by = data[3].trim();
            newTask = new Deadline(taskDescription,by);
            if(taskStatus.equals("1")){
                newTask.markedAsDone();
            }
        }else if(taskType.equals(TaskListEnum.E.toString())){
            //Event Task.
            String at = data[3].trim();
            newTask = new Event(taskDescription,at);
            if(taskStatus.equals("1")){
                newTask.markedAsDone();
            }
        }
        return newTask;
    }

}
