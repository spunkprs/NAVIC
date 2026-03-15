package multithreading.problems.concurrentCache;

public class ListNode<K, V> {

    private K key;
    private V value;
    private ListNode next;
    private ListNode previous;
    private Long ttl;
    private Long creationTime;

    public ListNode(K key, V value, long ttl) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
        this.creationTime = System.currentTimeMillis();
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
}
