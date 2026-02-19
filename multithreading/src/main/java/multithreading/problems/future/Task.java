package multithreading.problems.future;

public class Task {
    private int taskId;
    private long delayInExecution;

    public Task(int taskId, long delayInExecution) {
        this.taskId = taskId;
        this.delayInExecution = delayInExecution;
    }
}
