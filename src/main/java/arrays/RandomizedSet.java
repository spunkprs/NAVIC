package arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
	
	private ListNode headNode = null;
    private ListNode tailNode = null;
    private Map<Integer, ListNode> mapOne = new HashMap<Integer, ListNode>();
    private Map<Integer, ListNode> mapTwo = new HashMap<Integer, ListNode>();
    private int length = 0;
    
    
    public RandomizedSet() {
        
    }
    
    public boolean insert(int val) {
    	if (!mapTwo.containsKey(val)) {
    		if (headNode == null) {
                headNode = new ListNode(0, val);
                tailNode = headNode;
            } else {
                ListNode node = new ListNode(tailNode.index + 1, val);
                node.previous = tailNode;
                tailNode.next = node;
                tailNode = node;
            }
            mapOne.put(tailNode.index, tailNode);
            mapTwo.put(tailNode.value, tailNode);
            length++;
            return true;
    	}
    	return false;
    }
    
    
    public boolean remove(int val) {
        if (mapTwo.containsKey(val)) {
            ListNode node = tailNode;
            ListNode nodeFromMap = mapTwo.get(val);
            if (node == nodeFromMap) {
                if (node.previous == null) {
                    headNode = null;
                    tailNode = null;
                    mapOne.clear();
                    mapTwo.clear();
                } else {
                    ListNode previousNode = node.previous;
                    mapOne.remove(node.index);
                    mapTwo.remove(node.value);
                    previousNode.next = null;
                    tailNode = previousNode;
                }
            } else {
                int tempValue = nodeFromMap.value;
                nodeFromMap.value = node.value;
                node.value = tempValue;
                mapTwo.put(nodeFromMap.value, nodeFromMap);
                mapTwo.put(node.value, node);
                mapTwo.remove(node.value);
                mapOne.remove(node.index);
                ListNode previousNode = node.previous;
                previousNode.next = null;
                tailNode = previousNode;
            }
            length--;
            return true;
        }
        return false;
    }
    
    public int getRandom() {
        Random random = new Random();
        ListNode node = mapOne.get(random.nextInt(length));
        return node.value;
    }
    
    
    class ListNode {
        private int index;
        private int value;
        private ListNode next;
        private ListNode previous;
        
        public ListNode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

}
