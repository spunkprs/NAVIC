package list;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
	
	private int capacity;
	private ListNode headNode;
	private ListNode tailNode;
	
	private Map<Integer, Container> mapOne = new HashMap<Integer, Container>();
	private Map<Integer, LFUCacheNode> mapTwo = new HashMap<Integer, LFUCache.LFUCacheNode>();
	private Map<Integer, ListNode> mapThree = new HashMap<Integer, LFUCache.ListNode>();
	
	public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
    	if (!mapOne.containsKey(key)) {
    		return -1;
    	} else {
    		Container container = mapOne.get(key);
    		ListNode removedListNode = removeEntryFromMapAndRestructure(container);
    		container.frequency = container.frequency + 1;
    		pushEntryIntoMapAndRestructure(container, container.lfuCacheNode, removedListNode);
    		return container.lfuCacheNode.value;
    	}
    }
    
    private void pushEntryIntoMapAndRestructure(Container container, LFUCacheNode lfuCacheNode, ListNode removedListNode) {
    	if (container.frequency == 1) {
    		if (headNode == null) {
    			mapTwo.put(container.frequency, lfuCacheNode);
    			lfuCacheNode.previous = lfuCacheNode;
    			mapOne.put(lfuCacheNode.key, container);
    			headNode = new ListNode(container.frequency);
    			tailNode = headNode;
    			mapThree.put(container.frequency, headNode);
    		} else {
    			if (headNode.frequency == 1) {
    				LFUCacheNode head = mapTwo.get(headNode.frequency);
        			lfuCacheNode.next = head;
        			LFUCacheNode previousHeadNode = head.previous;
        			head.previous = lfuCacheNode;
        			lfuCacheNode.previous = previousHeadNode;
        			head = lfuCacheNode;
        			mapTwo.put(container.frequency, head);
        			mapOne.put(lfuCacheNode.key, container);
    			} else {
    				ListNode listNode = new ListNode(container.frequency);
    				listNode.next = headNode;
    				headNode.previous = listNode;
    				headNode = listNode;
    				mapThree.put(container.frequency, headNode);
    				lfuCacheNode.previous = lfuCacheNode;
    				mapTwo.put(container.frequency, lfuCacheNode);
    				mapOne.put(lfuCacheNode.key, container);
    			}
    		}
    	} else {
    		int frequency = container.frequency;
        	LFUCacheNode cacheNode = mapTwo.get(frequency);
        	ListNode listNode = mapThree.get(frequency);
        	if (cacheNode == null) {
        		cacheNode = container.lfuCacheNode;
        		if (removedListNode == null) {
        			listNode = mapThree.get(frequency - 1);
            		if (listNode.next == null) {
            			ListNode nextListNode = new ListNode(frequency);
            			listNode.next = nextListNode;
            			nextListNode.previous = listNode;
            			mapThree.put(frequency, nextListNode);
            			tailNode = nextListNode;
            		} else {
            			ListNode nextListNode = new ListNode(frequency);
            			nextListNode.previous = listNode;
            			ListNode nextListNodeOne = listNode.next;
            			nextListNodeOne.previous = nextListNode;
            			nextListNode.next = nextListNodeOne;
            			listNode.next = nextListNode;
            			mapThree.put(frequency, nextListNode);
            		}
        		} else {
        			ListNode removedListNodeNext = removedListNode.next;
        			ListNode removedListNodePrevious = removedListNode.previous;
        			ListNode newListNode = null;
        			if (removedListNodeNext == null && removedListNodePrevious == null) {
        				newListNode = new ListNode(frequency);
        				headNode = newListNode;
        				tailNode = headNode;
        			} else {
        				if (removedListNodeNext == null) {
            				newListNode = new ListNode(frequency);
            				tailNode.next = newListNode;
            				newListNode.previous = tailNode;
            				tailNode = newListNode;
            			} else if (removedListNodePrevious == null) {
            				newListNode = new ListNode(frequency);
            				newListNode.next = headNode;
            				headNode.previous = newListNode;
            				headNode = newListNode;
            			} else {
            				newListNode = new ListNode(frequency);
            				newListNode.next = removedListNodeNext;
            				newListNode.previous = removedListNodePrevious;
            			}
        			}
        			mapThree.put(frequency, newListNode);
        		}
        		cacheNode.previous = cacheNode;
        		mapTwo.put(container.frequency, cacheNode);
        		
        	} else {
            		LFUCacheNode cacheHeadNode = cacheNode;
            		cacheNode = container.lfuCacheNode;
            		LFUCacheNode cacheHeadNodePrevious = cacheHeadNode.previous;
            		cacheNode.next = cacheHeadNode;
            		cacheHeadNode.previous = cacheNode;
            		cacheNode.previous = cacheHeadNodePrevious;
            		cacheHeadNode = cacheNode;
            		mapTwo.put(container.frequency, cacheHeadNode);
        	}
    	}
	}

	private ListNode removeEntryFromMapAndRestructure(Container container) {
		ListNode removedListNode = null;
		int currentFrequency = container.frequency;
		LFUCacheNode currentNode = container.lfuCacheNode;
		if (mapTwo.containsKey(currentFrequency)) {
			LFUCacheNode cacheHeadNode = mapTwo.get(currentFrequency);
			LFUCacheNode cachePreviousNode = cacheHeadNode.previous;
			if (cacheHeadNode == currentNode) {
				if (cacheHeadNode.next == null) {
					ListNode listNode = mapThree.get(currentFrequency);
					if (listNode.previous == null) {
						headNode = listNode.next;
					} else if (listNode.next == null) {
						ListNode previousListNode = listNode.previous;
						previousListNode.next = null;
						tailNode = previousListNode;
					} else {
						ListNode previousNode = listNode.previous;
						ListNode nextNode = listNode.next;
						previousNode.next = nextNode;
						nextNode.previous = previousNode;
					}
					mapTwo.remove(currentFrequency);
					mapThree.remove(listNode.frequency);
					removedListNode = listNode;
				} else {
					LFUCacheNode cacheNextNode = cacheHeadNode.next;
					cacheNextNode.previous = cachePreviousNode;
					cacheHeadNode = cacheNextNode;
					mapTwo.put(container.frequency, cacheHeadNode);
				}
			} else {
				if (currentNode.next == null) {
					cacheHeadNode.previous = currentNode.previous;
					currentNode.previous.next = null;
				} else {
					LFUCacheNode previousCacheNode = currentNode.previous;
					LFUCacheNode nextCacheNode = currentNode.next;
					previousCacheNode.next = nextCacheNode;
					nextCacheNode.previous = previousCacheNode;
				}
			}
		}
		return removedListNode;
	}

	public void put(int key, int value) {
    	LFUCacheNode lfuCacheNode = new LFUCacheNode(key, value);
    	if (this.capacity > 0) {
    		if (!mapOne.containsKey(key)) {
            	if (mapOne.size() == this.capacity) {
            		int minimalFrequency = headNode.frequency;
            		LFUCacheNode cacheNodeHead = mapTwo.get(minimalFrequency);
            		LFUCacheNode cacheNodePrevious = cacheNodeHead.previous;
            		if (cacheNodeHead != cacheNodePrevious) {
            			cacheNodeHead.previous = cacheNodePrevious.previous;
            			cacheNodeHead.previous.next = null;
            		} else {
            			mapTwo.remove(headNode.frequency);
            			mapThree.remove(headNode.frequency);
            			headNode = headNode.next;
            		}
            		mapOne.remove(cacheNodePrevious.key);
            		Container container = new Container(1 , lfuCacheNode);
            		pushEntryIntoMapAndRestructure(container, lfuCacheNode, null);
            	} else {
            		Container container = new Container(1 , lfuCacheNode);
            		pushEntryIntoMapAndRestructure(container, lfuCacheNode, null);
            	}
            } else {
            	Container container = mapOne.get(key);
            	ListNode removedListNode = removeEntryFromMapAndRestructure(container);
        		container.frequency = container.frequency + 1;
            	pushEntryIntoMapAndRestructure(container, container.lfuCacheNode, removedListNode);
            	container.lfuCacheNode.value = value;
            	mapOne.put(key, container);
            }
    	}
    }
    
    static class LFUCacheNode {
    	private int key;
    	private int value;
    	private LFUCacheNode next;
    	private LFUCacheNode previous;
    	
    	public LFUCacheNode(int key, int value) {
    		this.key = key;
    		this.value = value;
    	}
    }
    
    static class Container {
    	private int frequency = 1;
    	private LFUCacheNode lfuCacheNode;
    	
    	public Container(int frequency, LFUCacheNode lfuCacheNode) {
    		this.frequency = frequency;
    		this.lfuCacheNode = lfuCacheNode;
    	}
    }
    
    static class ListNode {
    	private int frequency;
    	private ListNode next;
    	private ListNode previous;
    	
    	public ListNode(int frequency) {
    		this.frequency = frequency;
    	}
    }

}
