package ui;

import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;
import java.util.List;
import task.*;

import commands.CommandResult;

import static common.Messages.*;

public class TextUi {

    private final Scanner in; //= new Scanner(System.in);
    private final PrintStream out;

    public TextUi(){
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    public static final String LINE = "____________________________________________________________";

    public static final String LEGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Generates and prints the welcome message upon the start of the application.
     *
     * @param storageFilePath path to the storage file being used.
     */


//    public void showLine() {
//        System.out.println("____________________________________________________________");
//    }



//    //public void showLoadingError(){
//        System.out.println("No record found. Creating new file...");
//}


    public void showWelcome() {
        showToUser(
                LINE,
                MESSAGE_HELLO,
                LEGO,
                MESSAGE_WELCOME,
                LINE);
    }

    public void showGoodByeMessage() {
        showToUser(
                LINE,
                MESSAGE_GOODBYE,
                LINE);
    }

    public void showLoadingError() {
        showToUser(
                LINE,
                MESSAGE_INIT_FAILED,
                LINE);
    }

    public void showError(String message) {

        out.println("Error: " + message);
    }


    public void showInvalidInput() {
        showToUser(
                LINE,
                MESSAGE_INIT_FAILED,
                LINE);
    }

    public void showLine() {
        showToUser(LINE);
    }




    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public String readCommand() {
        return in.nextLine();
    }


    public void showNewAddedTask(){
        showToUser(
                LINE,
                MESSAGE_NEW_TASK_ADDED
        );

    }

    public void printAllTasks(List<Task> taskList){
        for (int i = 0; i < taskList.size(); i++){
            System.out.println(i+1 + ". " + taskList.get(i));
        }
    }



    public void printTask(String task){
        out.println("\t" + task);
    }

    public void printTaskCount(int count){
        String taskCount = String.format(MESSAGE_TASKLIST_COUNT,count);
        showToUser(
                LINE,
                taskCount
        );
    }

   public void printAddTask(String task,int count){
        showNewAddedTask();
        printTask(task);
        printTaskCount(count);

   }









}
