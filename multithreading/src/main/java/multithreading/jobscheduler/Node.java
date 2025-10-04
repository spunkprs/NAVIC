package multithreading.jobscheduler;

import java.util.List;

/**
List of associated tasks against a timestamp
 * */

public class Node {
    private long timestamp;
    private List<Task> associatedTasks;
}
