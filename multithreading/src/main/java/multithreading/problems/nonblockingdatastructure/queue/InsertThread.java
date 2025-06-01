package multithreading.problems.nonblockingdatastructure.queue;

public class InsertThread<T> implements Runnable {

    private String threadName;
    private T itemToBeInserted;
    private NonBlockingQueue<T> nonBlockingQueue;
    private long sleepTime;

    public InsertThread(String threadName, T itemToBeInserted, NonBlockingQueue<T> nonBlockingQueue, long sleepTime) {
        this.threadName = threadName;
        this.itemToBeInserted = itemToBeInserted;
        this.nonBlockingQueue = nonBlockingQueue;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {

    }
}
