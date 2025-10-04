package multithreading.jobscheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class Tester {

    public static void main(String ar[]) {
        PriorityBlockingQueue<Node> priorityBlockingQueue = new PriorityBlockingQueue<Node>(10, new TaskComparator());
        JobScheduler jobScheduler = new JobScheduler(priorityBlockingQueue);
        ExecutorService executorService = Executors.newCachedThreadPool();

        TaskOrchestrator taskOrchestrator = new TaskOrchestrator(priorityBlockingQueue, executorService, 1000);

        JobsRunner runner = new JobsRunner(taskOrchestrator);
    }
}
