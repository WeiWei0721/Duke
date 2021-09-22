public class TaskList {
    public String[] taskList = new String[100];
    int count;

    public TaskList(){
        count = 0;
    }

    public void addTask(String task){
        this.taskList[count] = task;
        this.count++;
        System.out.println("Added: " + task);
    }


    public void printList(){
        for(int i = 0; i<count;i++ ){
            System.out.println(i + 1 + ". " + this.taskList[i]);
        }
    }


}
