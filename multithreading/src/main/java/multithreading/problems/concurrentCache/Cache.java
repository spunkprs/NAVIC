package multithreading.problems.concurrentCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Cache<K, V> {

    private ListNode<K, V> listNodeHead;
    private ListNode<K, V> listNodeTail;
    private Map<K, ListNode<K, V>> internalMap = new HashMap<>();
    private int cacheSize;
    private int maxSize;

    private ReentrantLock reentrantLock = new ReentrantLock();

    public Cache(int maxSize) {
        this.maxSize = maxSize;
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

    private void removeNode(ListNode<K, V> node) {
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
}
