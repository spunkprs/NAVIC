package multithreading.jobscheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
Via this API task of any type can be added/removed from the scheduler
 * */

public class JobScheduler {

    private PriorityBlockingQueue<Node> priorityBlockingQueue;

    public JobScheduler(PriorityBlockingQueue<Node> priorityBlockingQueue) {
        this.priorityBlockingQueue = priorityBlockingQueue;
    }

    public void addTask(Task task) {
        List<Task> associatedTasks = new ArrayList<>();
        associatedTasks.add(task);
        Node node = new Node(task.getRunTimestamp());
        node.setAssociatedTasks(associatedTasks);
        priorityBlockingQueue.add(node);
    }

    public void removeTask(Task task) {
        task.setMarkedForRemoval(true);
    }
}
