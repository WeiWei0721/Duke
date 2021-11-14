import com.weiwei.task.Task;
import com.weiwei.task.TaskList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    static TaskList taskList = new TaskList();

    @BeforeAll
    public static void before() {
        taskList.addTask(new Task("duke task 1"));
        taskList.addTask(new Task("duke task 2"));
        taskList.addTask(new Task("duke task 3"));
        taskList.addTask(new Task("duke task 4"));
        taskList.addTask(new Task("duke task 5"));
    }

    @Test
    public void getTaskContainAnyKeyword() {
        String[] list = {"duke", "5"};
        Set<String> keywords = new HashSet<>(Arrays.asList(list));

        List<Task> result = taskList.getTaskContainAnyKeyword(keywords);
        assertEquals(5, result.size());
    }

    @Test
    public void getTaskContainAllKeyword() {
        String[] list = {"duke", "5"};
        Set<String> keywords = new HashSet<>(Arrays.asList(list));

        List<Task> result = taskList.getTaskContainAllKeyword(keywords);
        assertEquals(1, result.size());
    }
}