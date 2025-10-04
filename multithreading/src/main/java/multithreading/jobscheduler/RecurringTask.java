package multithreading.jobscheduler;

public class RecurringTask extends Task {

    private long recurringInterval;

    public RecurringTask(int taskId, long runTimestamp, int attemptsOnFailure, long recurringInterval) {
        super(taskId, runTimestamp, attemptsOnFailure);
        this.recurringInterval = recurringInterval;
    }

    @Override
    public Task getNextTask() {
        return new RecurringTask(super.getTaskId(), super.getRunTimestamp() + this.recurringInterval,
                super.getAttemptsOnFailure(), this.recurringInterval);
    }

    @Override
    public void run() {
        runTask();
    }
}
