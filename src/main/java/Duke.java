import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import storage.*;
import ui.*;
import task.*;
import parser.*;
import commands.*;

public class Duke {

    /**
     Ui: deals with interactions with the user
     Storage: deals with loading tasks from the file and saving tasks in the file
     Parser: deals with making sense of the user command
     TaskList: contains the task list e.g., it has operations to add/delete tasks in the list
     **/

    private StorageFile storage;
    private TaskList taskList;
    private TextUi ui;

    public Duke(String filePath){

        try{
            this.ui = new TextUi();
            this.storage = new StorageFile(filePath);
            this.taskList = storage.load();
        }catch (StorageFile.InvalidStorageFilePathException | StorageFile.StorageOperationException e){
            ui.showLoadingError();
            //tasks = new TaskList;
            throw new RuntimeException(e);
        }
    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit){
            try{
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = new Parser().parserCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            }catch (Exception e){
                ui.showError(e.getMessage());
            }finally {
                ui.showLine();
            }

        }
    }

    public static void main(String[] args){
        new Duke("data/task.txt").run();
    }



//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//
//        System.out.println("Hello from\n" + logo);
//
//        String line = "____________________________________________________________";
//        String bye = "\n\tBye. Hope to see you again soon!";
//        System.out.println(line + "\nHello! I'm Duke\n" + "What can I do for you?\n" + line);
//
//        String input;
//        Scanner in = new Scanner(System.in);
//
//        //String filePath;
//        String home = System.getProperty("user.home");
//        //System.out.println(home);
//
//        try{
//            File f = new File(home + File.separator + "Desktop" + File.separator + "Data" + File.separator + "Duke.txt");
//            //boolean fExist = false;
//            if(f.createNewFile()){
//                System.out.println("File created: " + f.getName() + "(" + f.getPath() + ")");
//
//            }else{// file exist , read data from file.
//                System.out.println("File already exists.");
//
//                Scanner s = new Scanner(f);
//
//
//            }
//        }catch (IOException e){
//            System.out.println("An error occurred.");
//        }
//
//
//
//
//
//        TaskList taskList = new TaskList();
//        //List<TaskList> taskList = new ArrayList<>();
//
//
//        while (true) {
//            input = in.nextLine();
//
//            if (input.equals("bye")) {
//                //exit chatBox.
//                System.out.println(line + bye + "\n" + line);
//                break;
//            } else if (input.equals("list")) {
//                //print list array.
//                System.out.println(line);
//                taskList.printList();
//                System.out.println(line);
//                //break;
//            } else if (input.startsWith("done")
//                    && Integer.parseInt(input.substring(5)) <= taskList.count) {
//                // mark Task as Done.
//                int taskNo = Integer.parseInt(input.substring(5)) - 1;
//
//                System.out.println(line);
//                taskList.markedAsDone(taskNo);
//                System.out.println(line);
//                //input = in.nextLine();
//
//            } else if (input.startsWith("todo")) {
//                // handle task with to do type .
//                String task = input.substring(5);
//
//                System.out.println(line);
//                taskList.addTaskAsTodo(task);
//                System.out.println(line);
//
//            } else if (input.startsWith("deadline")) {
//                // handle task with deadline type.
//                int num = input.indexOf("/");
//                String task = input.substring(9, num - 1);
//                String deadline = input.substring(num + 4);
//
//                //check task name
//                //System.out.println("check Task name: " + task + "\nDealine: "+ deadline);
//
//                System.out.println(line);
//                taskList.addTaskAsDeadline(task, deadline);
//                System.out.println(line);
//
//            } else if (input.startsWith("event")) {
//                //handle task with event type.
//                int num = input.indexOf("/");
//                String task = input.substring(6, num - 1);
//                String at = input.substring(num + 4);
//
//                //check task name
//                //System.out.println("check Task name: " + task + "\nat: "+ at);
//
//                System.out.println(line);
//                taskList.addTaskAsEvent(task, at);
//                System.out.println(line);
//
//
//            } else if (input.startsWith("delete")
//                    && Integer.parseInt(input.substring(7)) <= taskList.count) {
//                // delete task
//                int taskNo = Integer.parseInt(input.substring(7)) - 1;
//
//                System.out.println(line);
//                taskList.delTask(taskNo);
//                System.out.println(line);
//
//
//            } else {
//                // add new task into arraylist.
//                Task newTask = new Task(input);
//
//                System.out.println(line);
//                taskList.addTask(newTask);
//
//                System.out.println(line);
//            }
//
//            //System.out.println(line + input + "\n" + line);
//
//        }
//
//
//    }
}

