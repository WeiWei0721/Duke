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
        String bye = " Bye. Hope to see you again soon!";
        System.out.println(line + "\nHello! I'm Duke\n" + "What can I do for you?\n" + line);

        String input;
        Scanner in = new Scanner(System.in);

        //String[] list = new String[100];
        //int count = 0;
        TaskList taskList = new TaskList();

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
                break;
            }
            // add new task into arraylist.
            System.out.println(line);
            taskList.addTask(input);
            System.out.println(line);
                //System.out.println(line + input + "\n" + line);

        }



    }
}

