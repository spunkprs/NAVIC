package multithreading.problems.blockingqueue;

/*
Producer thread that pushes items into BlockingQueue
 */

public class ProducerThread<T> implements Runnable {

    private T item;
    private int number;
    private int sleepTimeInMillis;
    private BlockingQueue<T> blockingQueue;

    public ProducerThread(T item, int number, int sleepTimeInMillis, BlockingQueue<T> blockingQueue) {
        this.item = item;
        this.number = number;
        this.sleepTimeInMillis = sleepTimeInMillis;
        this.blockingQueue = blockingQueue;
    }



    @Override
    public void run() {
        try {
            Thread.sleep(sleepTimeInMillis);
            System.out.println(getDescription() + " " + Thread.currentThread() + " with id " + getNumber() + " pushing item " + getItem() + " in the queue " + blockingQueue.enqueue(item));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return "Producer";
    }

    public int getNumber() {
        return number;
    }

    public T getItem() {
        return item;
    }
}
