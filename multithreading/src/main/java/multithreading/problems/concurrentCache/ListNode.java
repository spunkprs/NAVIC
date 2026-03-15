package multithreading.problems.concurrentCache;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ListNode<K, V> implements Delayed {

    private K key;
    private V value;
    private ListNode next;
    private ListNode previous;
    private Long ttl;
    private Long creationTime;
    private long expirationTime;

    public ListNode(K key, V value, long ttl) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
        this.creationTime = System.currentTimeMillis();
        this.expirationTime = this.creationTime + this.ttl;
    }

    public ListNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void setPrevious(ListNode previous) {
        this.previous = previous;
    }

    public ListNode getNext() {
        return next;
    }

    public ListNode getPrevious() {
        return previous;
    }

    public Long getTtl() {
        return ttl;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    /**
    This method has very strong hidden purpose which is making the ListNode available only when this.expirationTime - System.currentTimeMillis() becomes 0.
     Methods like take() && poll() can be used to remove elements from the DelayedQueue poll() is non-blocking where as take() is blocking which means if none of the
     elements have expired poll() will return null immediately where as take() will be blocked until some element has expired

     Internally it works like PriorityQueue only that's why we are seeing compareTo() method below which is used to sort nodes based on their expirationTime
     * */

    @Override
    public long getDelay(TimeUnit unit) {
        long delay = this.expirationTime - System.currentTimeMillis();
        return unit.convert(delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        return Long.compare(this.expirationTime, ((ListNode)other).expirationTime);
    }
}
