package multithreading.problems.blockingqueue.tasksexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class LongRunningWorker extends Worker {

    private boolean isHalted;
    private AtomicInteger longRunningWorkerThreadCount;

    public LongRunningWorker(BlockingQueue<Task> blockingQueue, AtomicInteger longRunningWorkerThreadCount) {
        super(blockingQueue);
        this.isHalted = false;
        this.longRunningWorkerThreadCount = longRunningWorkerThreadCount;
    }

    @Override
    public void executeTask() {
        while (!isHalted) {
            try {
                Task task = this.getBlockingQueue().take();
                task.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.longRunningWorkerThreadCount.decrementAndGet();
            }
        }
    }
}
