package lld.jobScheduler.model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class JobRun implements Delayed {

    private String jobRunId;
    private long executionTimeInMillis;
    private Runnable attachedPayload;

    public JobRun(String jobRunId, long executionTimeInMillis, Runnable attachedPayload) {
        this.jobRunId = jobRunId;
        this.executionTimeInMillis = executionTimeInMillis;
        this.attachedPayload = attachedPayload;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.getExecutionTimeInMillis() - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS);
    }

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
