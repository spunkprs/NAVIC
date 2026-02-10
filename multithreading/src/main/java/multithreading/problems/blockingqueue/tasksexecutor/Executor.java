package multithreading.problems.blockingqueue.tasksexecutor;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Executor {

    private BlockingQueue<Task> blockingQueue;
    private List<Worker> longRunningWorkerThreads;
    private int initialCapacity;
    private int maxCapacity;
    private AtomicInteger shortRunningWorkerThreadCount;
    private AtomicInteger longRunningWorkerThreadCount;

    public Executor(int initialCapacity, int maxCapacity) {
        this.blockingQueue = new LinkedBlockingQueue<>(maxCapacity);
        for (int i = 1; i <= this.initialCapacity; i++) {
            longRunningWorkerThreads.add(new LongRunningWorker(this.blockingQueue, longRunningWorkerThreadCount));
        }

        startLongRunningWorkerThreads(this.longRunningWorkerThreads);
        longRunningWorkerThreadCount = new AtomicInteger(initialCapacity);
        shortRunningWorkerThreadCount = new AtomicInteger(0);
    }

    private void startLongRunningWorkerThreads(List<Worker> longRunningWorkerThreads) {
        for (Worker worker : longRunningWorkerThreads) {
            Thread t = new Thread(worker);
            t.start();
        }
    }

    public void submitTask(Task task) {
        if (this.blockingQueue.offer(task)) {
            System.out.print("Task submitted successfully");
        } else {
            if (longRunningWorkerThreadCount.get() + shortRunningWorkerThreadCount.get() < maxCapacity) { //This line has issues as I am comparing using get() method
                    Worker worker = new ShortRunningWorker(this.blockingQueue, this.shortRunningWorkerThreadCount); //Ideally group of threads shall start in one go instead of 1
                    Thread t = new Thread(worker);
                    t.start();
            } else {
                System.out.print("Task is going to be rejected !!");
            }
        }
    }
}
