import java.util.ArrayList;
import java.util.List;

public class TaskList extends Task {
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


    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + ". "
                    + "[" + this.taskList[i].getStatusIcon() + "] "
                    + this.taskList[i].description);
        }
    }

    //@Override
    public void markedAsDone(int taskNo) {
        this.taskList[taskNo].markedAsDone();
        //System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n"
                + "["+this.taskList[taskNo].getStatusIcon() + "] "
                + this.taskList[taskNo].getDescription());

    }

}
