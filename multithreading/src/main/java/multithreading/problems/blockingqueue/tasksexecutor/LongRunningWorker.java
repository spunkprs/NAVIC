package multithreading.problems.blockingqueue.tasksexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class LongRunningWorker extends Worker {

    private boolean isHalted;
    private AtomicInteger workerThreadCount;

    public LongRunningWorker(BlockingQueue<Task> blockingQueue, AtomicInteger workerThreadCount) {
        super(blockingQueue);
        this.isHalted = false;
        this.workerThreadCount = workerThreadCount;
    }

    @Override
    public void executeTask() {
        while (!isHalted) {
            try {
                Task task = this.getBlockingQueue().take(); //Correct of pulling task from queue in thread safe manner and it's a blocking call so in case queue is empty, thread will be disabled for thread scheduling
                task.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.workerThreadCount.decrementAndGet();
            }
        }
    }
}
