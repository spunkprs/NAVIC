package multithreading.problems.blockingqueue.tasksexecutor;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
Custom thread pool executor which supports following kind of internal Workers :-
a.) LongRunningWorker --> These tasks will always be there until got terminated because of some exception
b.) ShortRunningWorker --> To handle burst of tasks pushed inside BlockingQueue but count(LongRunningWorker) + count(ShortRunningWorker) < maxCapacity

Interesting point is both the workers will be pulling tasks from underlying BlockingQueue
 * */

public class Executor {

    private BlockingQueue<Task> blockingQueue;
    private List<Worker> longRunningWorkerThreads;
    private int initialCapacity;
    private int maxCapacity;
    private AtomicInteger workerThreadCount;

    public Executor(int initialCapacity, int maxCapacity) {
        this.blockingQueue = new LinkedBlockingQueue<>(maxCapacity);
        for (int i = 1; i <= this.initialCapacity; i++) {
            longRunningWorkerThreads.add(new LongRunningWorker(this.blockingQueue, workerThreadCount));
        }

        startLongRunningWorkerThreads(this.longRunningWorkerThreads);
        workerThreadCount = new AtomicInteger(initialCapacity);
    }

    private void startLongRunningWorkerThreads(List<Worker> longRunningWorkerThreads) {
        for (Worker worker : longRunningWorkerThreads) {
            Thread t = new Thread(worker);
            t.start();
        }
    }

    public void submitTask(Task task) {
        if (this.blockingQueue.offer(task)) { // Correct way of enqueuing the task in thread safe manner if BlockingQueue is not full as it will return false
            System.out.print("Task submitted successfully");
        } else {
            if (workerThreadCount.get() < maxCapacity) { //This line has issues as I am comparing using get() method [Need to revisit]
                    Worker worker = new ShortRunningWorker(this.blockingQueue, this.workerThreadCount); //Ideally group of threads shall start in one go instead of 1
                    Thread t = new Thread(worker);
                    t.start();
            } else {
                System.out.print("Task is going to be rejected !!");
            }
        }
    }
}
