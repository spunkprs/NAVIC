package multithreading.dag;

import java.util.List;

public abstract class Job {
    private int jobId;
    private long runTimestamp;
    private boolean isMarkedForRemoval;

    public Job(int jobId, long runTimestamp) {
        this.jobId = jobId;
        this.runTimestamp = runTimestamp;
    }

    private List<Task> dependentTasks;

    public abstract void runAssociatedTasks();

    public abstract Job fetchNextJob();

    public List<Task> getDependentTasks() {
        return dependentTasks;
    }

    public int getJobId() {
        return jobId;
    }

    public long getRunTimestamp() {
        return runTimestamp;
    }
}
