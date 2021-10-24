package storage;


import task.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {
    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */

    public static void encodeTaskList(Task toSave,  String pathOfFileToSave) throws IOException{
        try{
            String encodeTask = encodeTasktoString(toSave);
            FileWriter fw = new FileWriter(pathOfFileToSave);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(encodeTask);
            bw.newLine();
            bw.close();
        }catch (Exception e){
            e.getMessage();
        }
    }
//    public static ArrayList<Task> encodeTaskList(TaskList toSave){
//        final ArrayList<Task> encodeTasks = new ArrayList<>();
//
//        toSave.forEach(task -> encodeTasks.add(encodeTasktoString(task)));
//
//        }
//    }

    /**
     * Encodes the {@code Task} into a decodable and readable string representation.
     */
    private static String encodeTasktoString(Task task){

        final StringBuilder encodeTaskListBuilder = new StringBuilder();

        encodeTaskListBuilder.append(task.getType());
        encodeTaskListBuilder.append(" | ");
        encodeTaskListBuilder.append(task.getStatusIcon().equals("X") ? "1" : 0);
        encodeTaskListBuilder.append(" | ");
        encodeTaskListBuilder.append(task.getDescription());


        return encodeTaskListBuilder.toString();
    }

//    private static StringBuilder appendEncodedTask(StringBuilder sb, Task task){
//        sb.append(task.getType());
//        sb.append(" | ");
//        sb.append(task.getStatusIcon().equals("X") ? "1" : 0);
//        sb.append(" | ");
//        sb.append(task.getDescription());
//        return sb;
//
//    }



}
