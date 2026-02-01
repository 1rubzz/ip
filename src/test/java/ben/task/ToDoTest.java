package ben.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void returnStatusDone() {
        ToDo curr = new ToDo("Read book");
        curr.markAsDone();
        String status = curr.returnStatus();

        assertEquals("[T][X] Read book", status);
    }

    @Test
    public void returnCorrectStorageFormat() {
        ToDo curr = new ToDo("Read book");
        String stored = curr.fromTaskToString();

        assertEquals("T | 0 | Read book", stored);
    }

}
