package multithreading.jobscheduler;

public abstract class Task implements Runnable {

    private int taskId;
    private long runTimestamp;
    private int attemptsOnFailure;
    private int currentAttemptNumber;
    private boolean hasFailedAtLeastOnce;
    private boolean isMarkedForRemoval;
    private TaskType taskType;

    public Task(int taskId, long runTimestamp, int attemptsOnFailure) {
        this.taskId = taskId;
        this.runTimestamp = runTimestamp;
        this.attemptsOnFailure = attemptsOnFailure;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
    Logic for handling retries upon failure till max available attempts is also taken care of
     * */

    public void runTask() {
        try {
            if (!hasFailedAtLeastOnce) {
                System.out.println("Task id " + this.taskId + " of type " + getTaskType().name() +
                        " going to run on the timestamp " + this.runTimestamp + " against attempt number " + currentAttemptNumber);
            } else {
                    currentAttemptNumber++;
                    System.out.println("Task id " + this.taskId + " of type " + getTaskType().name() +
                            " going to run on the timestamp " + System.currentTimeMillis() + " against attempt number " + currentAttemptNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (!hasFailedAtLeastOnce) {
                hasFailedAtLeastOnce = true;
            }
            if (currentAttemptNumber < attemptsOnFailure) {
                System.out.println("Task Id " + this.taskId + " going for retry !!");
                runTask();
            }
        }
    }

    public abstract Task getNextTask();

    public int getTaskId() {
        return taskId;
    }

    public long getRunTimestamp() {
        return runTimestamp;
    }

    public int getAttemptsOnFailure() {
        return attemptsOnFailure;
    }

    public boolean isMarkedForRemoval() {
        return isMarkedForRemoval;
    }

    public void setMarkedForRemoval(boolean markedForRemoval) {
        isMarkedForRemoval = markedForRemoval;
    }
}
