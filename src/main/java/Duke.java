import commands.Command;
import commands.CommandEnum;
import parser.Parser;
import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class Duke {

    /**
     TextUi: deals with interactions with the user
     StorageFile: deals with loading tasks from the file and saving tasks in the file
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
        }catch (StorageFile.StorageOperationException e){
            ui.showLoadingError();
            throw new RuntimeException(e);
        }
    }

    public void run(){

        ui.showWelcome();
        boolean isExit = false;
        Parser p = new Parser();
        while(!isExit){
            try{
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = p.parserCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            }catch (Exception e){
                ui.showToUser(e.getMessage());
            }finally {
                ui.showLine();
            }

        }
    }

    public static void main(String[] args){

        new Duke("data/Duke.txt").run();
    }

}

