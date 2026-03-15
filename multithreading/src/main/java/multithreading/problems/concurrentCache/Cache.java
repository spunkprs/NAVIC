package multithreading.problems.concurrentCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
Making sure each Cache node guarantees consistency, though I understand concern of throughput is definitely there
To tackle concern of throughput I have wrapped it around CacheService and make every Cache node behave like a segment instead, this will up to a great extent will handle
concern of throughput, key can be used to compute hash which will be used to distribute traffic across different cache nodes
 * */

public class Cache<K, V> {

    private ListNode<K, V> listNodeHead;
    private ListNode<K, V> listNodeTail;
    private Map<K, ListNode<K, V>> internalMap = new HashMap<>();
    private int cacheSize;
    private int maxSize;
    private DelayQueue<ListNode<K, V>> delayQueue;

    private ReentrantLock reentrantLock = new ReentrantLock();

    public Cache(int maxSize) {
        this.maxSize = maxSize;
        this.delayQueue = new DelayQueue<>();
    }

    public void put(K key, V value, Long ttl) {
        try {
            reentrantLock.lock();
            ListNode<K, V> newListNode = null;
            newListNode = ttl != null ? new ListNode<>(key, value, ttl) : new ListNode<>(key, value);
            if (cacheSize >= this.maxSize) {
                evictNode();
            }
            pushNodeToCache(newListNode);
            delayQueue.offer(newListNode);
        } catch (Exception e) {
            System.out.print("Exception while pushing key to the cache !!");
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void pushNodeToCache(ListNode<K, V> newListNode) {
        if (listNodeHead == null) {
            listNodeTail = newListNode;
            listNodeHead = listNodeTail;
        } else {
            listNodeTail.setNext(newListNode);
            newListNode.setPrevious(listNodeTail);
            listNodeTail = listNodeTail.getNext();
        }
        internalMap.put(newListNode.getKey(), newListNode);
        cacheSize++;
    }

    private void evictNode() {
        K key = listNodeHead.getKey();
        internalMap.remove(key, listNodeHead);
        listNodeHead = listNodeHead.getNext();
        listNodeHead.setPrevious(null);
        cacheSize--;
    }

    /**
    Why locking is being used inside get() method call because of reason below :-
     a.) Two operations are getting performed inside this get() method, first fetching value against the provided key
     and second ordering of concerned ListNode needs to be altered too
     b.) Because of the two reasons above mentioned, these shall be considered atomic operation instead otherwise if gaps are there we will see better throughput but
     inconsistent results
     * */

    public V get(K key) {
        try {
            reentrantLock.lock();
            ListNode<K, V> node = internalMap.get(key);

            if (node != null) {
                boolean action = node.getTtl() != null ? System.currentTimeMillis() - node.getCreationTime() >= node.getTtl() : false;
                if (node.getTtl() == null || !action) {
                    ListNode<K, V> listNode = internalMap.get(key);
                    ListNode<K, V> listNodeNext = listNode.getNext();
                    ListNode<K, V> listNodePrev = listNode.getPrevious();
                    if (listNode == listNodeHead) {
                        listNodeTail.setNext(listNodeHead);
                        listNodeHead.setPrevious(listNodeTail);
                        listNodeHead = listNodeNext;
                    } else if (listNode != listNodeTail) {
                        listNodeTail.setNext(listNode);
                        listNodePrev.setNext(listNodeNext);
                        listNodeNext.setPrevious(listNodePrev);
                    }
                    listNodeTail = listNodeTail.getNext();
                    return listNode.getValue();
                } else {
                    removeNode(node);
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.print("Exception while fetching key from the cache !!");
            e.printStackTrace();
            throw e;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void remove(K key) {
        reentrantLock.lock();
        try {
            if (internalMap.containsKey(key)) {
                ListNode<K, V> node = internalMap.get(key);
                removeNode(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    protected void removeNode(ListNode<K, V> node) {
        internalMap.remove(node.getKey(), node);
        if (node == listNodeHead) {
            listNodeHead = listNodeHead.getNext();
            listNodeHead.setPrevious(null);
        } else if (node == listNodeTail) {
            ListNode<K, V> prevNode = listNodeTail.getPrevious();
            prevNode.setNext(null);
            listNodeTail = prevNode;
        } else {
            ListNode<K, V> prevNode = node.getPrevious();
            ListNode<K, V> nextNode = node.getNext();

            prevNode.setNext(nextNode);
            nextNode.setPrevious(prevNode);
        }
    }

    public DelayQueue<ListNode<K, V>> getDelayQueue() {
        return delayQueue;
    }

    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }
}
