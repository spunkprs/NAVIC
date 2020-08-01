package list;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	
	private int capacity;
	private LRUCacheNode head;
	private LRUCacheNode tail;
	
	private Map<Integer, LRUCacheNode> map = new HashMap<Integer, LRUCacheNode>();
	
	public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
    	if (!map.containsKey(key)) {
    		return -1;
    	} else {
    		LRUCacheNode node = map.get(key);
    		restructureList(node);
    		return map.get(key).value;
    	}
    }
    
    private void restructureList(LRUCacheNode node) {
		LRUCacheNode previousNode = node.previous;
		LRUCacheNode nextNode = node.next;
		
		if (previousNode != null && nextNode != null) {
			previousNode.next = nextNode;
			nextNode.previous = previousNode;
			tail.next = node;
			node.previous = tail;
			node.next = null;
			tail = node;
		} else if (previousNode == null && nextNode != null) {
			head = nextNode;
			tail.next = node;
			node.next = null;
			node.previous = tail;
			tail = node;
			head.previous = null;
		}
	}

	public void put(int key, int value) {
		LRUCacheNode node = new LRUCacheNode(key, value);
        if (map.size() == this.capacity) {
        	if (!map.containsKey(key)) {
        		int headKey = head.key;
            	map.remove(headKey);
            	if (this.capacity == 1) {
            		head = null;
            		tail = null;
            		head = node;
            		tail = node;
            	} else {
            		head = head.next;
                	head.previous = null;
                	tail.next = node;
                	node.previous = tail;
                	tail = node;
            	}
        	} else {
        		node = map.get(key);
        		node.value = value;
        		restructureList(node);
        	}
        } else {
        	if (!map.containsKey(key)) {
        		if (head == null) {
            		head = node;
            		tail = node;
            	} else {
            		tail.next = node;
            		node.previous = tail;
            		tail = node;
            	}
        	} else {
        		node = map.get(key);
        		node.value = value;
        		restructureList(node);
        	}
        }
        map.put(key, node);
    }
    
    static class LRUCacheNode {
    	private int key;
    	private int value;
    	private LRUCacheNode next;
    	private LRUCacheNode previous;
    	
    	public LRUCacheNode(int key, int value) {
    		this.key = key;
    		this.value = value;
    	}
    }

}
