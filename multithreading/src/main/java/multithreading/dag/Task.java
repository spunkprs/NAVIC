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

    }

    public void setChildren(List<Task> children) {
        this.children = children;
    }

    public void setParent(List<Task> parent) {
        this.parent = parent;
    }
}
