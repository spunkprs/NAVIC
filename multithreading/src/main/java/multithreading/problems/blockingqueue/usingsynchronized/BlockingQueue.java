package multithreading.problems.blockingqueue.usingsynchronized;


/*
This class aims at presenting or coming up with producer/consumer problem solution, here are the following important points around it :-
a.) Here we have Blocking Queue which will be blocked for inserts once it reaches it's maximum size && it's blocked for pops once it's size is zero
b.) Have made use of singly linked list as an internal data structure {inserts are made at tail && removal from the head}
c.) Operations on BlockingQueue are mutually exclusive
d.) Only two operations {push && pop} are allowed
e.) Mutual exclusion is achieved using java monitor pattern i.e intrinsic lock
* */

public class BlockingQueue <T> {

    private int capacity;
    private int sizeOfTheQueue;
    private Node<T> head;
    private Node<T> tail;
    private Object lock;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        lock = new Object();
    }

    public T enqueue(T item) throws Exception {
        synchronized (lock) {
            try {
                while (sizeOfTheQueue == capacity) {
                    System.out.println(Thread.currentThread() + " Waiting for one of the consumer process to pull data from the queue before any further push");
                    lock.wait();
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
                lock.notifyAll();
                return item;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    public T dequeue() throws Exception {
        Node nodeToBeDeleted = null;

        synchronized (lock) {
            try {
                while (sizeOfTheQueue == 0) {
                    System.out.println(Thread.currentThread() + " Waiting for one of the producer process to push data into the queue before any further pull");
                    lock.wait();
                }
                if (sizeOfTheQueue > 0) {
                    nodeToBeDeleted = head;
                    head = head.next;
                    sizeOfTheQueue--;
                    lock.notifyAll();
                }
                return (T) nodeToBeDeleted.item;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
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

}
