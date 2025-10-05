package multithreading.dag;

public class NonRecurringJob extends Job {

    public NonRecurringJob(int jobId, long runTimestamp) {
        super(jobId, runTimestamp);
    }

    @Override
    public void runAssociatedTasks() {

    }

    @Override
    public Job fetchNextJob() {
        return null;
    }
}
