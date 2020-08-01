package arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomizedSetWithDuplicates {
	
	private ListNode headNode = null;
    private ListNode tailNode = null;
    private Map<Integer, Container> mapOne = new HashMap<Integer, Container>();
    private Map<Integer, ListNode> mapTwo = new HashMap<Integer, ListNode>();
    private int length = 0;
	
	public RandomizedSetWithDuplicates() {
        
    }
	
	public boolean insert(int val) {
		if (!mapOne.containsKey(val)) {
			if (headNode == null) {
				headNode = new ListNode(0, val);
				tailNode = headNode;
			} else {
				ListNode node = new ListNode(tailNode.index + 1, val);
				tailNode.next = node;
				node.previous = tailNode;
				tailNode = node;
			}
			Container containerTailNode = new Container(tailNode);
			mapOne.put(tailNode.value, containerTailNode);
			mapTwo.put(tailNode.index, tailNode);
			length++;
			return true;
		} else {
			Container containerTailNode = mapOne.get(val);
			ListNode node = new ListNode(tailNode.index + 1, val);
			tailNode.next = node;
			node.previous = tailNode;
			tailNode = node;
			Container containerNode = new Container(tailNode);
			containerTailNode.next = containerNode;
			containerNode.previous = containerTailNode;
			mapOne.put(val, containerNode);
			mapTwo.put(tailNode.index, tailNode);
			length++;
		}
        return false;
    }
	
	public boolean remove(int val) {
		if (!mapOne.containsKey(val)) {
			return false;
		} else {
			Container containerNode = mapOne.get(val);
			ListNode listNode = containerNode.node;
			ListNode tempTailNode = null;
			if (listNode == tailNode) {
				if (tailNode.previous == null) {
					headNode = null;
					tailNode = null;
					length--;
					mapOne.clear();
					mapTwo.clear();
					return true;
				} else {
					ListNode previousNode = tailNode.previous;
					previousNode.next = null;
					tempTailNode = tailNode;
					tailNode = previousNode;
					mapTwo.remove(tempTailNode.index);
					length--;
				}
				if (containerNode.previous == null) {
					if (mapOne.containsKey(containerNode.node.value)) {
						mapOne.remove(containerNode.node.value);
					}
				} else {
					Container previousContainerNode = containerNode.previous;
					previousContainerNode.next = null;
					mapOne.put(previousContainerNode.node.value, previousContainerNode);
				}
				return true;
			} else if (listNode == headNode) {
				if (headNode.next == null) {
					headNode = null;
					tailNode = null;
					length--;
					mapOne.clear();
					mapTwo.clear();
					return true;
				} else {
					ListNode nextNode = headNode.next;
					
					int headNodeIndex = headNode.index;
					int tailNodeIndex = tailNode.index;
					
					ListNode previousTailNode = tailNode.previous;
					if (previousTailNode != headNode) {
						nextNode.previous = tailNode;
						tailNode.next = nextNode;
						tailNode.index = headNodeIndex;
						previousTailNode.next = headNode;
						headNode.previous = previousTailNode;
						
						headNode = nextNode.previous;
						tailNode = previousTailNode.next;
						
						mapTwo.put(headNodeIndex, headNode);
						mapTwo.put(tailNodeIndex, tailNode);
						mapTwo.remove(tailNodeIndex);
						previousTailNode.next = null;
						tailNode = previousTailNode;
					} else {
						mapTwo.put(headNodeIndex, tailNode);
						mapTwo.remove(tailNodeIndex);
						headNode = tailNode;
						headNode.next = null;
						headNode.previous = null;
						headNode.index = headNodeIndex;
					}
					
					length--;
					
					if (containerNode.previous == null) {
						if (mapOne.containsKey(containerNode.node.value)) {
							mapOne.remove(containerNode.node.value);
						}
					} else {
						Container previousContainerNode = containerNode.previous;
						previousContainerNode.next = null;
						mapOne.put(previousContainerNode.node.value, previousContainerNode);
					}
				}
				return true;
			} else {
				ListNode previousNode = listNode.previous;
				ListNode nextNode = listNode.next;
				int indexOfNode = listNode.index;
				int tailNodeIndex = tailNode.index;
				
				ListNode previousTailNode = tailNode.previous;
				
				previousNode.next = tailNode;
				tailNode.previous = previousNode;
				tailNode.next = nextNode;
				nextNode.previous = tailNode;
				tailNode.index = indexOfNode;
				
				mapTwo.put(indexOfNode, tailNode);
				mapTwo.put(tailNodeIndex, listNode);
				
				previousTailNode.next = null;
				tailNode = previousTailNode;
				
				mapTwo.remove(tailNodeIndex);
				length--;
				
				if (containerNode.previous == null) {
					if (mapOne.containsKey(containerNode.node.value)) {
						mapOne.remove(containerNode.node.value);
					}
				} else {
					Container previousContainerNode = containerNode.previous;
					previousContainerNode.next = null;
					mapOne.put(previousContainerNode.node.value, previousContainerNode);
				}
				return true;
			}
		}
    }
	
	public int getRandom() {
		Random random = new Random();
		ListNode node = mapTwo.get(random.nextInt(length));
		if (node != null) {
			return node.value;
		}
		return -1;
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
	
	class Container {
		private ListNode node;
		private Container next;
		private Container previous;
		
		public Container(ListNode node) {
			this.node = node;
		}
	}

}
