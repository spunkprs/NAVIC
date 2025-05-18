package multithreading.problems.blockingqueue;

public class ConsumerThread<T> implements Runnable {

    private int number;
    private int sleepTimeInMillis;
    private BlockingQueue<T> blockingQueue;

    public ConsumerThread(int number, int sleepTimeInMillis, BlockingQueue<T> blockingQueue) {
        this.number = number;
        this.sleepTimeInMillis = sleepTimeInMillis;
        this.blockingQueue = blockingQueue;
    }

    public String getDescription() {
        return "Consumer";
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTimeInMillis);
            System.out.println(getDescription() + " " + Thread.currentThread() + " with id " + getNumber() + " pulling item " + blockingQueue.dequeue() + " from the queue ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
