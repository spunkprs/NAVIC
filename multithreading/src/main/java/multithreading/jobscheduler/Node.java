package multithreading.jobscheduler;

import java.util.List;

/**
List of associated tasks against a timestamp
 * */

public class Node {
    private long timestamp;
    private List<Task> associatedTasks;

    public Node(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<Task> getAssociatedTasks() {
        return associatedTasks;
    }
}
