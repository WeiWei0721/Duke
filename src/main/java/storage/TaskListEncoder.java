package storage;


import task.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import parser.*;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {
    /**
     * Encodes a single {@code Task} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static void encodeTask(Task toSave, String pathOfFileToSave) throws IOException{
        try{
            String encodedTask = encodeTaskToString(toSave);
            FileWriter fw = new FileWriter(pathOfFileToSave,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(encodedTask);
            bw.newLine();
            bw.close();
        }catch (Exception e){
            e.getMessage();
        }
    }



    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */

    public static void encodeTaskList(List<Task> toSave,  String pathOfFileToSave) throws IOException{
        final List<String> encodeTaskList = new ArrayList<>();
        FileWriter fw = new FileWriter(pathOfFileToSave,false);//not append , overwrite.
        BufferedWriter bw = new BufferedWriter(fw);
        for(Task individualTask : toSave){
            encodeTaskList.add(encodeTaskToString(individualTask));
        }
        for(String singleTask : encodeTaskList){
            bw.write(singleTask);
            bw.newLine();
        }
        bw.close();
//        try{
//            String encodeTask = encodeTasktoString(toSave);
//            FileWriter fw = new FileWriter(pathOfFileToSave);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(encodeTask);
//            bw.newLine();
//            bw.close();
//        }catch (Exception e){
//            e.getMessage();
//        }
    }


    /**
     * Encodes the {@code Task} into a decodable and readable string representation.
     */
    private static String encodeTaskToString(Task task){

        StringBuilder encodeTaskListBuilder = new StringBuilder();

        if(task instanceof Todo){
            encodeTaskListBuilder.append(TaskListEnum.T);
            encodeTaskListBuilder = appendEncodedTask(encodeTaskListBuilder,task);
        }else if(task instanceof Deadline){
            encodeTaskListBuilder.append(TaskListEnum.D);
            encodeTaskListBuilder = appendEncodedTask(encodeTaskListBuilder,task);
            Deadline d = (Deadline)task;
            encodeTaskListBuilder.append(" | ");
            encodeTaskListBuilder.append(Parser.parseDateForStorage(d.getBy()));
        }else if(task instanceof Event){
            encodeTaskListBuilder.append(TaskListEnum.E);
            encodeTaskListBuilder = appendEncodedTask(encodeTaskListBuilder,task);
            Event e = (Event)task;
            encodeTaskListBuilder.append(Parser.parseDateForStorage(e.getAt()));
        }
//        encodeTaskListBuilder.append(task.getType());
//        encodeTaskListBuilder.append(" | ");
//        encodeTaskListBuilder.append(task.getStatusIcon().equals("X") ? "1" : 0);
//        encodeTaskListBuilder.append(" | ");
//        encodeTaskListBuilder.append(task.getDescription());


        return encodeTaskListBuilder.toString();
    }

    /**
     * Helper method that construct the String for the encoded Task in the text field
     * @param sb StringBuilder
     * @param task Task
     * @return Partial Construction of the task in the specified txt file
     */
    private static StringBuilder appendEncodedTask(StringBuilder sb, Task task){
        sb.append(" | ");
        sb.append(task.getStatusIcon().equals("X") ? "1" : 0);
        sb.append(" | ");
        sb.append(task.getDescription());
        return sb;

    }



}
