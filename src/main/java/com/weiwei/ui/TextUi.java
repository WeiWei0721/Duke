package com.weiwei.ui;

import com.weiwei.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static com.weiwei.common.Messages.*;

public class TextUi {

    private final Scanner in; //= new Scanner(System.in);
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
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
                ///MESSAGE_HELLO,
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


    public void showNewAddedTask() {
        showToUser(
                MESSAGE_NEW_TASK_ADDED
        );

    }

    public void showMarkDoneTask(Task task) {
        showToUser(
                MESSAGE_TASK_MARK_DONE,
                task.toString()
        );
    }

    public void showDeleTask() {
        showToUser(
                MESSAGE_DELE_TASK_DONE
        );
    }

    public void printAllTasks(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    public void showCommands(List<String> commandList) {
        for (int i = 0; i < commandList.size(); i++) {
            System.out.println(i + 1 + ". " + commandList.get(i));
        }
    }


    public void printTask(Task task) {
        out.println("\t"
                + task);
    }

    public void printTaskCount(int count) {
        String taskCount = String.format(MESSAGE_TASKLIST_COUNT, count);
        showToUser(
                taskCount
        );
    }

    public void printAddTask(Task task, int count) {
        showNewAddedTask();
        printTask(task);
        printTaskCount(count);

    }

    public void printDeleTask(Task task, int count) {
        showDeleTask();
        printTask(task);
        printTaskCount(count);
    }

    public void showFindTaskByKeywords(List<Task> taskFound) {
        if (taskFound.size() > 0) {
            printAllTasks(taskFound);
        } else {
            showToUser(
                    MESSAGE_TASK_FOUND_FAILED
            );
        }

    }


    public void showFormatFileMessage() {
        showToUser(
                MESSAGE_FORMAT_TASKLIST
        );
    }
}
