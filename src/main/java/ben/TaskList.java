package ben;

import java.util.ArrayList;
import ben.Ui;
import ben.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<String> input) throws BenException {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            tasks.add(Task.fromStringToTask(input.get(i)));
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getNoOfTasks() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

}
