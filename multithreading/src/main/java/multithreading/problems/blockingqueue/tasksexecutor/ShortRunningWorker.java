package multithreading.problems.blockingqueue.tasksexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ShortRunningWorker extends Worker {

    private AtomicInteger shortRunningWorkerThreadCount;

    public ShortRunningWorker(BlockingQueue<Task> blockingQueue, AtomicInteger shortRunningWorkerThreadCount) {
        super(blockingQueue);
        this.shortRunningWorkerThreadCount = shortRunningWorkerThreadCount;
    }

    @Override
    public void executeTask() {
            try {
                shortRunningWorkerThreadCount.incrementAndGet();
                Task task = this.getBlockingQueue().take();
                task.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                shortRunningWorkerThreadCount.decrementAndGet();
            }
    }
}
