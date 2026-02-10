package multithreading.problems.blockingqueue.tasksexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ShortRunningWorker extends Worker {

    private AtomicInteger workerThreadCount;

    public ShortRunningWorker(BlockingQueue<Task> blockingQueue, AtomicInteger workerThreadCount) {
        super(blockingQueue);
        this.workerThreadCount = workerThreadCount;
    }

    @Override
    public void executeTask() {
            try {
                workerThreadCount.incrementAndGet();
                Task task = this.getBlockingQueue().take(); //Correct of pulling task from queue in thread safe manner and it's a blocking call so in case queue is empty, thread will be disabled for thread scheduling
                task.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                workerThreadCount.decrementAndGet();
            }
    }
}
