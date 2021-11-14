import com.weiwei.exception.BusinessException;
import com.weiwei.storage.StorageFile;
import com.weiwei.storage.TaskListDecoder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListDecoderTest {

    @Test
    void decodeTaskList_validEncodedTaskList_success() throws StorageFile.StorageOperationException, BusinessException {
        List<String> encodedTaskList = new ArrayList<>();
        encodedTaskList.add("T | 0 | todo task description");
        encodedTaskList.add("D | 0 | deadline task description | 08/11/2021 2359");
        encodedTaskList.add("E | 0 | event task description | 08/11/2021 1400 - 08/11/2021 1600");

        assertEquals(3, TaskListDecoder.decodeTaskList(encodedTaskList).getSize());

    }
}