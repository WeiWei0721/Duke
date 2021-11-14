import com.weiwei.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @org.junit.jupiter.api.Test
    void todoConstructor_withDescription_success() {
        Todo t = new Todo("todo description");
        assertEquals("todo description", t.getDescription());
    }

    @Test
    public void toString_validToDo_success() {
        Todo t = new Todo("Testing junit");
        assertEquals("[T][ ] Testing junit", t.toString());
    }
}