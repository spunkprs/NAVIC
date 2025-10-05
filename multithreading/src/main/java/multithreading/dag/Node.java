package multithreading.dag;

import java.util.Objects;

public class Node {
    private int jobId;
    private Task task;

    public Node(int jobId, Task task) {
        this.jobId = jobId;
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return jobId == node.jobId && task.getTaskId() == node.task.getTaskId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, task.getTaskId());
    }

    public Task getTask() {
        return task;
    }
}
