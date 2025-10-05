package multithreading.dag;

import java.util.List;

public class Task implements Runnable {

    private int jobId;
    private int taskId;
    private TaskState state;
    private int retryAttempts;

    public Task(int jobId, int taskId, TaskState state, int retryAttempts) {
        this.jobId = jobId;
        this.taskId = taskId;
        this.state = state;
        this.retryAttempts = retryAttempts;
    }

    private List<Task> children;
    private List<Task> parent;

    @Override
    public void run() {
        //Post completion it's status shall be changed to SUCCESS
        //In the event of failures retries shall be made till allowed retryAttempts
    }

    public void setChildren(List<Task> children) {
        this.children = children;
    }

    public void setParent(List<Task> parent) {
        this.parent = parent;
    }

    public List<Task> getParent() {
        return parent;
    }

    public int getJobId() {
        return jobId;
    }

    public int getTaskId() {
        return taskId;
    }

    public TaskState getState() {
        return state;
    }

    public List<Task> getChildren() {
        return children;
    }
}
