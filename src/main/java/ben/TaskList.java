package ben;

import java.util.ArrayList;

import ben.task.Task;

/**
 * Represents a list of tasks in the application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a task list from the given list of task data.
     *
     * @param input List of task data loaded from storage.
     * @throws BenException If corrupted task data is detected.
     */
    public TaskList(ArrayList<String> input) throws BenException {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            tasks.add(Task.fromStringToTask(input.get(i)));
        }
    }

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks.
     */
    public int getNoOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to be removed.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    // Level 9: Find
    /**
     * Returns the list of tasks currently stored in this task list.
     *
     * @return An arrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }


}
