package multithreading.explicitlocking;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerImplementationUsingReentrantLock {

    /*
    * Beautiful articulation of Producer && Consumer OR Blocking Queue using Reentrant Locks, here I have made producer faster
    * compared to Consumer, vice-versa can also be done
    * Following links were pretty useful in deep diving of reentrant lock concepts :-
    * 0.) https://krishnakishorev.medium.com/java-multithreading-concurrency-and-parallelism-part-14-899b02be7cb6
    * 1.) https://krishnakishorev.medium.com/java-multithreading-concurrency-and-parallelism-part-15-a18e94a65d94
    * 2.) https://krishnakishorev.medium.com/java-multithreading-concurrency-and-parallelism-part-16-7604cca34b95
    * 3.) https://www.linkedin.com/pulse/java-suspended-thread-states-blocked-waiting-timedwaiting-ycrash/
    * 4.) https://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.State.html
    * */
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread producer = new Thread(new Producer(queue, lock, condition, 5));
        Thread consumer = new Thread(new Consumer(queue, lock, condition));

        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable {

        private Queue<Integer> queue;
        private Lock lock;
        private Condition condition;
        private int maxAllowedSize;

        public Producer(Queue queue, Lock lock, Condition condition, int maxAllowedSizeOfQueue) {
            this.queue = queue;
            this.lock = lock;
            this.condition = condition;
            this.maxAllowedSize = maxAllowedSizeOfQueue;
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    if (queue.size() == maxAllowedSize) {
                        System.out.println("Producer is waiting for consumer to consume ::");
                        condition.await();
                        System.out.println("Producer is back into RUNNABLE State ::");
                    } else {
                        int generatedNumber = new Random().nextInt(6);
                        queue.add(generatedNumber);
                        System.out.println("Producer pushed number " + generatedNumber + " to the queue !!");
                        condition.signal();
                        lock.unlock();
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            produce();
        }
    }

    static class Consumer implements Runnable {

        private Queue<Integer> queue;
        private Lock lock;
        private Condition condition;

        public Consumer(Queue queue, Lock lock, Condition condition) {
            this.queue = queue;
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    if (queue.size() == 0) {
                        System.out.println("Consumer is waiting for producer to produce ::");
                        condition.await();
                    } else {
                        int num = queue.peek();
                        System.out.println("Consumer pulled number " + num + " from the queue !!");
                        queue.poll();
                        condition.signal();
                        lock.unlock();
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
