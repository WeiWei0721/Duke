import java.util.ArrayList;
import java.util.List;

public class TaskList {
    //public String[] taskList = new String[100];

    //create array tasklist with size of 100 tasks.
    Task[] taskList = new Task[100];
    int count;

    public TaskList() {
        count = 0;
    }

    public void addTask(String task) {
        this.taskList[count] = new Task(task);
        this.count++;
        System.out.println("Added: " + task);
    }

    public void addTaskAsTodo(String task) {
        this.taskList[count] = new Todo(task);
        //this.taskList[count].setType("T");
        this.count++;
        System.out.println("Got it. I've added this task: \n"
                + "\t[T] [ ] "
                + task
                + "\nNow you have " + count + " tasks in the list.");

    }

    public void addTaskAsDeadline(String task, String by) {
        this.taskList[count] = new Deadline(task, by);
        this.count++;
        System.out.println("Got it. I've added this task: \n"
                + "\t[D] [ ] "
                + task
                + " (by: " + by + ")"
                + "\nNow you have " + count + " tasks in the list.");
    }

    public void addTaskAsEvent(String task, String at){
        this.taskList[count] = new Event(task,at);
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

            System.out.println(i + 1 + ". " + this.taskList[i]);

        }
    }

    //@Override
    public void markedAsDone(int taskNo) {
        this.taskList[taskNo].markedAsDone();
        //System.out.println("____________________________________________________________");
//        System.out.println("Nice! I've marked this task as done:\n"
//                + "[" + this.taskList[taskNo].getStatusIcon() + "] "
//                + this.taskList[taskNo].getDescription());

        System.out.println("Nice! I've marked this task as done:\n" + this.taskList[taskNo]);

    }

}
