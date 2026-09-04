package lld.jobScheduler.model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
Responsible for holding the payload && time at which payload needs to be executed
It has to implement interface Delayed because this is something which we will be pushing to DelayedQueue
 * */

public class JobRun implements Delayed {

    private String jobRunId;
    private long executionTimeInMillis;
    private Runnable attachedPayload;

    public JobRun(String jobRunId, long executionTimeInMillis, Runnable attachedPayload) {
        this.jobRunId = jobRunId;
        this.executionTimeInMillis = executionTimeInMillis;
        this.attachedPayload = attachedPayload;
    }

    /***
     This method is used to fetch the delay i.e
     a.) > 0 [Task not ready to be picked]
     b.) <= 0 [Task ready to be picked]
     This method is called by the DelayedQueue when caller makes call to take() method of DelayedQueue --> which means poll object from DelayedQueue
     */

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.getExecutionTimeInMillis() - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS);
    }

    /***
    This method is used to sort the Delayed tasks[on the similar lines of PriorityQueue]
     */

    @Override
    public int compareTo(Delayed other) {
        JobRun otherJob = (JobRun) other;
        return Long.compare(this.getExecutionTimeInMillis(),
                otherJob.getExecutionTimeInMillis());
    }

    public String getJobRunId() {
        return jobRunId;
    }

    public long getExecutionTimeInMillis() {
        return executionTimeInMillis;
    }

    public Runnable getAttachedPayload() {
        return attachedPayload;
    }
}
