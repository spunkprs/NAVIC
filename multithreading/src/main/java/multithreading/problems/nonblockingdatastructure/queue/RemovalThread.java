package multithreading.problems.nonblockingdatastructure.queue;

public class RemovalThread<T> implements Runnable {

    private String threadName;
    private NonBlockingQueue<T> nonBlockingQueue;
    private long sleepTime;

    public RemovalThread(String threadName, NonBlockingQueue<T> nonBlockingQueue, long sleepTime) {
        this.threadName = threadName;
        this.nonBlockingQueue = nonBlockingQueue;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {

    }
}
