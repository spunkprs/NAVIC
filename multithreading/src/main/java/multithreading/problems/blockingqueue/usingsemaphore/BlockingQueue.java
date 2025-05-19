package multithreading.problems.blockingqueue.usingsemaphore;

import java.util.concurrent.Semaphore;

/*
This class aims at presenting or coming up with producer/consumer problem solution, here are the following important points around it :-
a.) Here we have Blocking Queue which will be blocked for inserts once it reaches it's maximum size && it's blocked for pops once it's size is zero
b.) Have made use of singly linked list as an internal data structure {inserts are made at tail && removal from the head}
c.) Operations on BlockingQueue are mutually exclusive
d.) Only two operations {push && pop} are allowed
e.) Mutual exclusion is achieved using Binary Semaphore that will work just like intrinsic && explicit lock as per functionality is concerned
f.) Have made use of two semaphores produceSemaphore && consumeSemaphore, their names are self explanatory

Important points to be noticed here are following :-

0.) Calls like acquire() && release() defined inside Semaphore class are atomic

1.) Number of permits against produceSemaphore is equal to the capacity of BoundedBuffer which also means that at max
these only produce threads will be able to push items into the queue until any of the item is consumed via consumer, this thing is made sure
by the permit count which once become zero blocks all the threads attempting to acquire the permit till the time permit > 0

2.) Number of permits against consumeSemaphore is equal to 0, which states that consumer threads will be blocked till it's value is greater than 0
which is being controlled by the producer thread i.e that dictates something is being pushed into the queue and then incrementing permit by 1 at this
point of time consumeSemaphore.acquire() call inside consumer thread is non blocking

3.) Binary Semaphore is being used like a intrinsic OR explicit lock allowing only one thread to enter into critical section atomically hence
guarantee correctness

* */

public class BlockingQueue<T> {

    private int capacity;
    private int sizeOfTheQueue;
    private Node<T> head;
    private Node<T> tail;
    private Semaphore produceSemaphore;
    private Semaphore consumeSemaphore;
    private Semaphore binarySemaphore;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.produceSemaphore = new Semaphore(capacity);
        this.consumeSemaphore = new Semaphore(0);
        this.binarySemaphore = new Semaphore(1);
    }

    public T enqueue(T item) throws Exception {
        try {
            produceSemaphore.acquire();
            binarySemaphore.acquire();
            if (head == null) {
                head = new Node<T>(item);
                tail = head;
            } else {
                Node<T> node = new Node<>(item);
                tail.next = node;
                tail = node;
            }
            sizeOfTheQueue++;
            consumeSemaphore.release();
            binarySemaphore.release();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public T dequeue() throws Exception {
        Node nodeToBeDeleted = null;
        try {
            consumeSemaphore.acquire();
            binarySemaphore.acquire();

            if (sizeOfTheQueue > 0) {
                nodeToBeDeleted = head;
                head = head.next;
                sizeOfTheQueue--;
                produceSemaphore.release();
                binarySemaphore.release();
            }
            return (T)nodeToBeDeleted.item;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
