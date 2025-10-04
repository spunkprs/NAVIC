package multithreading.jobscheduler;

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

    }

    public void removeTask(Task task) {

    }
}
