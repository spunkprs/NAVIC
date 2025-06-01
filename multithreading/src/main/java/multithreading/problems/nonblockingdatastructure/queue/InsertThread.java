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
        System.out.println(threadName + " proceeding with insertion of of element " + itemToBeInserted + " to the tail of non blocking queue !!");
        try {
            Thread.sleep(sleepTime);
            nonBlockingQueue.push(itemToBeInserted);
            System.out.println(threadName + " inserted element " +  itemToBeInserted + " to the tail of non blocking queue successfully !!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
