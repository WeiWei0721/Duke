package task;
import java.util.*;

import exception.*;


public class TaskList {
    //public String[] taskList = new String[100];

    //create array tasklist with size of 100 tasks.
    //Task[] taskList = new Task[100];

    //create a ArrayList to store task. - level 6
    ArrayList<Task> taskList = new ArrayList<Task>();
    int count;

    public TaskList() {
        count = 0;
    }

    public List<Task> getAllTasks(){
        return taskList;
    }

    public void addTask(Task newTask) {
//            throws DuplicateTaskException{
//        if(contains(newTask)){
//            throw new DuplicateTaskException();
//        }
        this.taskList.add(newTask);
        this.count++;
        //System.out.println("Added: " + newTask.getDescription());
    }

//    public void addTaskAsTodo(String task) {
//        //this.taskList[count] = new Todo(task);
//        //this.taskList[count].setType("T");
//
//        Todo newTodo = new Todo(task);
//        this.taskList.add(newTodo);
//
//        this.count++;
//        System.out.println("Got it. I've added this task: \n"
//                + "\t[T] [ ] "
//                + task
//                + "\nNow you have " + count + " tasks in the list.");
//
//    }

//    public void addTaskAsDeadline(String task, String by) {
//        //this.taskList[count] = new Deadline(task, by);
//
//        Deadline newDdl = new Deadline(task, by);
//        this.taskList.add(newDdl);
//
//        this.count++;
//        System.out.println("Got it. I've added this task: \n"
//                + "\t[D] [ ] "
//                + task
//                + " (by: " + by + ")"
//                + "\nNow you have " + count + " tasks in the list.");
//    }

//    public void addTaskAsEvent(String task, String at) {
//        //this.taskList[count] = new Event(task, at);
//
//        Event newEvent = new Event(task, at);
//        this.taskList.add(newEvent);
//
//        this.count++;
//        System.out.println("Got it. I've added this task: \n"
//                + "\t[E] [ ] "
//                + task
//                + " (at: " + at + ")"
//                + "\nNow you have " + count + " tasks in the list.");
//
//    }


    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            //System.out.println(i + 1 + ". " + this.taskList[i]);
            System.out.println(i + 1 + ". " + this.taskList.get(i));
        }
    }

    //@Override
    public void markedAsDone(int taskNo) throws TaskNotFoundException {
        //this.taskList[taskNo].markedAsDone();
        if(!contains(taskList.get(taskNo))){
            throw new TaskNotFoundException(taskNo+1);
        }else{
            this.taskList.get(taskNo).markedAsDone();
        }

        //System.out.println("Nice! I've marked this task as done:\n" + this.taskList.get(taskNo));
    }

    public void delTask(int taskNo) throws TaskNotFoundException  { //missing TaskNotFoundException ,handle later, after message settle

        if(contains(taskList.get(taskNo))){
            taskList.remove(taskNo);
            count--;
        }else{
            throw new TaskNotFoundException(taskNo);
        }

    }

    public int getSize(){
        return count;
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
