package multithreading.dag;

import java.util.Objects;

public class Node {
    private int jobId;
    private int taskId;

    public Node(int jobId, int taskId) {
        this.jobId = jobId;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return jobId == node.jobId && taskId == node.taskId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, taskId);
    }
}
