package multithreading.intrinsiclocks;

import java.util.LinkedList;
import java.util.Queue;

public class TypicalProducerConsumerProblem {

    /*
    This is a typical P&C problem where Producer will be pushing elements to a Queue of a required size && Consumer will be removing elements from the
    Queue, in case Queue reaches to it's maximum allowed size then Producer will wait for Consumer to consume on the similar note when size of Queue is zero
    then Consumer will wait for Producer to push elements into it
    Here I have seen problem of starvation, hence will be making use of explicit locking in the following segments to
    avoid it
    Starvation : https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html
    * */
    public static void main(String[] args) {
        Queue queue = new LinkedList();
        Processor processor = new Processor(1, 5, 5);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                processor.produce(queue);
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                processor.consume(queue);
            }
        });

        producer.start();
        consumer.start();
    }

    static class Processor {

        private int initialValue;
        private int size;
        private int maxValue;

        public Processor(int initialValue, int size, int maxValue) {
            this.initialValue = initialValue;
            this.size = size;
            this.maxValue = maxValue;
        }

        private Object lock = new Object();

        public void produce(Queue queue) {
            int startingValue = initialValue;
            while (true) {
                synchronized (lock) {
                    if (queue.size() == size) {
                        System.out.println("Producer is waiting for consumer to consume !!");
                        initialValue = startingValue;
                        relinquishIntrinsicLock();
                    } else {
                        System.out.println("Producer is adding " + initialValue + " to the queue !!");
                        queue.add(initialValue);
                        if (initialValue == maxValue) {
                            initialValue = startingValue;
                        } else {
                            initialValue +=1;
                        }
                        lock.notify();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        public void consume(Queue queue) {
            while (true) {
                synchronized (lock) {
                    if (queue.size() == 0) {
                        System.out.println("Consumer is waiting for producer to produce !!");
                        relinquishIntrinsicLock();
                    } else {
                        System.out.println("Consumer is removing " + queue.peek() + " from the queue !!");
                        queue.poll();
                        lock.notify();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        private void relinquishIntrinsicLock() {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
