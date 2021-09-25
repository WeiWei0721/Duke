import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";
        String bye = "\n\tBye. Hope to see you again soon!";
        System.out.println(line + "\nHello! I'm Duke\n" + "What can I do for you?\n" + line);

        String input;
        Scanner in = new Scanner(System.in);

        //String[] list = new String[100];
        //int count = 0;


        TaskList taskList = new TaskList();
        //List<TaskList> taskList = new ArrayList<>();
        

        while (true) {
            input = in.nextLine();

            if (input.equals("bye")) {
                //exit chatBox.
                System.out.println(line + bye + "\n" + line);
                break;
            } else if (input.equals("list")) {
                //print list array.
                System.out.println(line);
                taskList.printList();
                System.out.println(line);
                //break;
            } else if (input.startsWith("done")
                    && Integer.parseInt(input.substring(5)) <= taskList.count) {
                // mark Task as Done.
                int taskNo = Integer.parseInt(input.substring(5)) - 1;

                System.out.println(line);
                taskList.markedAsDone(taskNo);
                System.out.println(line);
                //input = in.nextLine();

            } else if (input.startsWith("todo")) {
                // handle task with to do type .
                String task = input.substring(5);

                System.out.println(line);
                taskList.addTaskAsTodo(task);
                System.out.println(line);

            } else if (input.startsWith("deadline")) {
                // handle task with deadline type.
                int num = input.indexOf("/");
                String task = input.substring(9, num - 1);
                String deadline = input.substring(num + 4);

                //check task name
                //System.out.println("check Task name: " + task + "\nDealine: "+ deadline);

                System.out.println(line);
                taskList.addTaskAsDeadline(task, deadline);
                System.out.println(line);

            } else if (input.startsWith("event")) {
                //handle task with event type.
                int num = input.indexOf("/");
                String task = input.substring(6, num - 1);
                String at = input.substring(num + 4);

                //check task name
                //System.out.println("check Task name: " + task + "\nat: "+ at);

                System.out.println(line);
                taskList.addTaskAsEvent(task, at);
                System.out.println(line);


            } else {
                // add new task into arraylist.
                System.out.println(line);
                taskList.addTask(input);
                System.out.println(line);
            }

            //System.out.println(line + input + "\n" + line);

        }


    }
}

