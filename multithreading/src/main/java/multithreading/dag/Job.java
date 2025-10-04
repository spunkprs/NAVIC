package multithreading.dag;

import java.util.List;

public abstract class Job {
    private int jobId;
    private long runTimestamp;
    private boolean isMarkedForRemoval;

    private List<Task> dependentTasks;
}
