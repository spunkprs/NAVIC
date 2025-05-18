package multithreading.problems.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueImplementationUsingLock {

    static class BlockingQueue <T> {
        private int capacity;
        private int sizeOfTheQueue;
        private Node<T> head;
        private Node<T> tail;
        private Lock lock;
        private Condition isFull;
        private Condition isEmpty;

        public BlockingQueue(int capacity) {
            this.capacity = capacity;
            lock = new ReentrantLock();
            isFull = lock.newCondition();
            isEmpty = lock.newCondition();
        }

        public T enqueue(T item) {
            try {
                lock.lock();
                while (sizeOfTheQueue == capacity) {
                    System.out.println(Thread.currentThread() + " Waiting for one of the consumer process to pull data from the queue before any further push");
                    isFull.await();
                    //lock.lock();
                }
                if (head == null) {
                    head = new Node<T>(item);
                    tail = head;
                } else {
                    Node<T> node = new Node<>(item);
                    tail.next = node;
                    tail = node;
                }
                sizeOfTheQueue++;
                isEmpty.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                return item;
            }
        }

        public T dequeue() {
            Node nodeToBeDeleted = null;
            try {
                lock.lock();
                while (sizeOfTheQueue == 0) {
                    System.out.println(Thread.currentThread() +" Waiting for one of the producer process to push data into the queue before any further pull");
                    isEmpty.await();
                    //lock.lock();
                }
                if (sizeOfTheQueue > 0) {
                    nodeToBeDeleted = head;
                    head = head.next;
                    sizeOfTheQueue--;
                    isFull.signalAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                return (T)nodeToBeDeleted.item;
            }

        }
    }

    static class Node<T> {
        T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
        }
    }

    public static void main(String ar[]) {
        BlockingQueue<String> blockingQueue = new BlockingQueue<>(3);

        Thread producerThreadOne = new Thread(new ProducerThread<String>("1", 1, 100, blockingQueue));
        Thread producerThreadTwo = new Thread(new ProducerThread<String>("2", 2, 200, blockingQueue));
        Thread producerThreadThree = new Thread(new ProducerThread<String>("3", 3, 300, blockingQueue));
        Thread producerThreadFour = new Thread(new ProducerThread<String>("4", 4, 400, blockingQueue));
        Thread producerThreadFive = new Thread(new ProducerThread<String>("5", 5, 500, blockingQueue));

        producerThreadOne.start();
        producerThreadTwo.start();
        producerThreadThree.start();
        producerThreadFour.start();
        producerThreadFive.start();

        Thread consumerThreadOne = new Thread(new ConsumerThread<String>(1, 0, blockingQueue));
        Thread consumerThreadTwo = new Thread(new ConsumerThread<String>(2, 0, blockingQueue));
        Thread consumerThreadThree = new Thread(new ConsumerThread<String>(3, 0, blockingQueue));
        Thread consumerThreadFour = new Thread(new ConsumerThread<String>(4, 100, blockingQueue));
        Thread consumerThreadFive = new Thread(new ConsumerThread<String>(5, 200, blockingQueue));

        consumerThreadOne.start();
        consumerThreadTwo.start();
        consumerThreadThree.start();
        consumerThreadFour.start();
        consumerThreadFive.start();

    }


}
