package ben.task;

import ben.BenException;
import ben.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class DeadlineTest {
    @Test
    public void addMultipleTasks() throws BenException {
        TaskList list = new TaskList();

        Deadline curr = new Deadline("2026-02-01", "Submit assignment");
        Deadline curr2 = new Deadline("2000-10-10", "Submit assignment 2");

        list.add(curr);
        list.add(curr2);

        // Verify the list size
        assertEquals(list.size(), 2);

        // mark one of them
        curr2.markAsDone();

        // Verify the correct ordering
        assertSame(curr, list.get(0),
                "First task should be the first deadline added");
        assertSame(curr2, list.get(1),
                "Second task should be the second deadline added");
    }
}
