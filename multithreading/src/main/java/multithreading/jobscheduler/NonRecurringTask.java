package multithreading.jobscheduler;

public class NonRecurringTask extends Task {

    public NonRecurringTask(int taskId, long runTimestamp, int attemptsOnFailure) {
        super(taskId, runTimestamp, attemptsOnFailure);
    }

    @Override
    public Task getNextTask() {
        return null;
    }

    @Override
    public void run() {
        runTask();
    }
}
