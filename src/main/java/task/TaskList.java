package task;
import java.util.*;

import exception.*;


public class TaskList {

    ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList() {

    }

    public List<Task> getAllTasks(){
        return taskList;
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);

    }



    public void printList() {
        if(taskList.size() > 0){
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + this.taskList.get(i));
            }
        }else{
            System.out.println("Empty List!!!");
        }

    }

    //@Override
    public void markedAsDone(int taskNo) throws TaskNotFoundException {
        if(!contains(taskList.get(taskNo))){
            throw new TaskNotFoundException(taskNo+1);
        }else{
            this.taskList.get(taskNo).markedAsDone();
        }

    }

    public void delTask(int taskNo) throws TaskNotFoundException  {

        if(contains(taskList.get(taskNo))){
            taskList.remove(taskNo);
        }else{
            throw new TaskNotFoundException(taskNo);
        }

    }

    public int getSize(){
        return taskList.size();
    }

    public void clear() {taskList.clear();}

    public boolean contains(Task toCheck){
        for(Task task : taskList){
            if(task.isSameTask(toCheck)){
                return true;
            }
        }
        return false;
    }

    public Task getTask(int taskNo) throws TaskNotFoundException{
        if(contains(taskList.get(taskNo))){
            return taskList.get(taskNo);
        }else{
            throw new TaskNotFoundException(taskNo);
        }
    }

    public static class DuplicateTaskException extends DuplicateDataException{
        protected DuplicateTaskException(){
            super("Operation would result in duplicate task");
        }
    }

    public List<Task> getTaskContainAnyKeyword(Set<String> keywords){
        final List<Task> matchTasks = new ArrayList<>();
        for(Task task : taskList){
            final Set<String>wordsInTask = new HashSet<>(task.getWordsInTask());
            if(!Collections.disjoint(wordsInTask,keywords)){
                matchTasks.add(task);
            }
        }

        return matchTasks;
    }





}
