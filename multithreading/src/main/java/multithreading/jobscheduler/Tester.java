package multithreading.jobscheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
tester code for simulating the behaviour of job scheduler
Reference material : https://akhiilgupta.medium.com/design-a-multi-threaded-task-scheduler-lld-multi-threaded-construct-eb090c5a8727
 * */

public class Tester {

    public static void main(String ar[]) {
        PriorityBlockingQueue<Node> priorityBlockingQueue = new PriorityBlockingQueue<Node>(10, new TaskComparator());
        JobScheduler jobScheduler = new JobScheduler(priorityBlockingQueue);
        ExecutorService executorService = Executors.newCachedThreadPool();

        TaskOrchestrator taskOrchestrator = new TaskOrchestrator(priorityBlockingQueue, executorService, 5000);

        long currentTime = System.currentTimeMillis();
        Task taskTwo = new RecurringTask(2, currentTime + 500, 2, 4000);
        taskTwo.setTaskType(TaskType.RECURRING);
        Task taskOne = new NonRecurringTask(1, currentTime + 1000, 2);
        taskOne.setTaskType(TaskType.NON_RECURRING);

        jobScheduler.addTask(taskOne);
        jobScheduler.addTask(taskTwo);

        JobsRunner runner = new JobsRunner(taskOrchestrator);
        runner.startProcess();
    }
}
