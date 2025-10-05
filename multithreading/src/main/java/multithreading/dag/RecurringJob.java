package multithreading.dag;

public class RecurringJob extends Job {

    private long recurringInterval;

    public RecurringJob(int jobId, long runTimestamp, long recurringInterval) {
        super(jobId, runTimestamp);
        this.recurringInterval = recurringInterval;
    }

    @Override
    public void runAssociatedTasks() {

    }

    @Override
    public Job fetchNextJob() {
        return new RecurringJob(super.getJobId(),
                super.getRunTimestamp() + this.recurringInterval, this.recurringInterval);
    }
}
