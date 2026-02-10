package multithreading.problems.blockingqueue.tasksexecutor;

import java.util.concurrent.BlockingQueue;

public abstract class Worker implements Runnable {

    private BlockingQueue<Task> blockingQueue;

    public Worker(BlockingQueue<Task> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public BlockingQueue<Task> getBlockingQueue() {
        return blockingQueue;
    }

    public abstract void executeTask();

    @Override
    public void run() {
        executeTask();
    }
}
