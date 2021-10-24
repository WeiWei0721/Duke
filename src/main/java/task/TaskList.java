package task;
import java.util.ArrayList;
import java.util.List;
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

    public void addTask(Task newTask) {
//            throws DuplicateTaskException{
//        if(contains(newTask)){
//            throw new DuplicateTaskException();
//        }
        this.taskList.add(newTask);
        this.count++;
        //System.out.println("Added: " + newTask.getDescription());
    }

    public void addTaskAsTodo(String task) {
        //this.taskList[count] = new Todo(task);
        //this.taskList[count].setType("T");

        Todo newTodo = new Todo(task);
        this.taskList.add(newTodo);

        this.count++;
        System.out.println("Got it. I've added this task: \n"
                + "\t[T] [ ] "
                + task
                + "\nNow you have " + count + " tasks in the list.");

    }

    public void addTaskAsDeadline(String task, String by) {
        //this.taskList[count] = new Deadline(task, by);

        Deadline newDdl = new Deadline(task, by);
        this.taskList.add(newDdl);

        this.count++;
        System.out.println("Got it. I've added this task: \n"
                + "\t[D] [ ] "
                + task
                + " (by: " + by + ")"
                + "\nNow you have " + count + " tasks in the list.");
    }

    public void addTaskAsEvent(String task, String at) {
        //this.taskList[count] = new Event(task, at);

        Event newEvent = new Event(task, at);
        this.taskList.add(newEvent);

        this.count++;
        System.out.println("Got it. I've added this task: \n"
                + "\t[E] [ ] "
                + task
                + " (at: " + at + ")"
                + "\nNow you have " + count + " tasks in the list.");

    }


    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            //System.out.println(i + 1 + ". " + this.taskList[i]);
            System.out.println(i + 1 + ". " + this.taskList.get(i));
        }
    }

    //@Override
    public void markedAsDone(int taskNo) {
        //this.taskList[taskNo].markedAsDone();
        this.taskList.get(taskNo).markedAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + this.taskList.get(taskNo));
    }

    public void delTask(int taskNo) { //missing TaskNotFoundException ,handle later, after message settle
        System.out.println("Noted. I've removed this task: \n" +
                "\t[" + this.taskList.get(taskNo).getType() + "] "
                + "[" + this.taskList.get(taskNo).getStatusIcon() + "]"
                + this.taskList.get(taskNo).getDescription());

        taskList.remove(taskNo);
        count--;
        System.out.println("Now you have " + count + " tasks in the list.");

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

    public static class DuplicateTaskException extends DuplicateDataException{
        protected DuplicateTaskException(){
            super("Operation would result in duplicate task");
        }
    }

    public static class TaskNotFoundException extends Exception {}



}
