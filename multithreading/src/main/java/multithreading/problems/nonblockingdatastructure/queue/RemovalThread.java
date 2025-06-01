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
        System.out.println(threadName + " proceeding with removal of head element from the non blocking queue !!");
        try {
            Thread.sleep(sleepTime);
            nonBlockingQueue.pop();
            System.out.println(threadName + " removed head element from the non blocking queue successfully !!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
