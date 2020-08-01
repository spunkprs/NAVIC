package list;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MinimumWindowSubstring {

	Map <Character, Queue<ListNode>> mapOne = new HashMap<Character, Queue<ListNode>>();
	ListNode head = null;
	ListNode tail = null;
	int minimumWindowSubStringLength = Integer.MAX_VALUE;
	int startIndex = -1;
	int endIndex = -1;
	
	public String minWindow(String s, String t) {
		boolean flag = false;
		char arrInput[] = s.toCharArray();
		char arrT[] = t.toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		if (arrT.length > arrInput.length) {
			return "";
		}
		
		for  (int i = 0; i < arrT.length; i++) {
			if (!map.containsKey(arrT[i])) {
				map.put(arrT[i], 1);
			} else {
				map.put(arrT[i], map.get(arrT[i]) + 1);
			}
		}
		
		int count = 0;
		for (int i = 0; i < arrInput.length; i++) {
			if (map.containsKey(arrInput[i])) {
				if (mapOne.containsKey(arrInput[i])) {
					int elementOccurence = map.get(arrInput[i]);
					if (elementOccurence == 1) {
						Queue<ListNode> queue = mapOne.get(arrInput[i]);
						ListNode listNode = queue.peek();
						if (listNode.previous == null && listNode.next != null) {
							head = listNode.next; 
							head.previous = null; 
							tail.next = listNode;
							listNode.previous = tail;
							listNode.next = null;
							tail = listNode;
						} else if (listNode.next == null) {
							tail = listNode;
						} else {
							ListNode previousNode = listNode.previous;
							ListNode nextNode = listNode.next;
							nextNode.previous = previousNode;
							previousNode.next = nextNode;
							tail.next = listNode;
							listNode.previous = tail;
							tail = listNode;
						}
						listNode.next = null;
						listNode.index = i;
					} else {
						Queue<ListNode> queue = mapOne.get(arrInput[i]);
						if (queue.size() == map.get(arrInput[i])) {
							ListNode existingListNode = queue.poll();
							ListNode newListNode = new ListNode(arrInput[i], i);
							queue.add(newListNode);
							
							if (existingListNode.previous == null) {
								ListNode nextNode = existingListNode.next;
								nextNode.previous = null;
								head = nextNode;
								tail.next = newListNode;
								newListNode.previous = tail;
								tail = newListNode;
							} else if (existingListNode.next == null) {
								ListNode previousListNode = existingListNode.previous;
								previousListNode.next = newListNode;
								newListNode.previous = previousListNode;
								tail = newListNode;
							} else {
								ListNode previousListNode = existingListNode.previous;
								ListNode nextNode = existingListNode.next;
								previousListNode.next = nextNode;
								nextNode.previous = previousListNode;
								tail.next = newListNode;
								newListNode.previous = tail;
								tail = newListNode;
							}
							
						} else if (queue.size() < map.get(arrInput[i])) {
							ListNode listNode = new ListNode(arrInput[i], i);
							queue.add(listNode);
							tail.next = listNode;
							listNode.previous = tail;
							tail = listNode;
							count++;
						}
					}
					
				} else {
					ListNode listNode = new ListNode(arrInput[i], i);
					Queue<ListNode> queue = new LinkedList<ListNode>();
					queue.add(listNode);
					mapOne.put(arrInput[i], queue);
					if (head == null) {
						head = listNode;
						tail = listNode;
					} else {
						tail.next = listNode;
						listNode.previous = tail;
						tail = listNode;
					}
					count++;
				}
			}
			if (count == arrT.length) {
				if ((tail.index - head.index + 1) < minimumWindowSubStringLength) {
					minimumWindowSubStringLength = tail.index - head.index + 1;
					startIndex = head.index;
					endIndex = tail.index;
				}
				flag = true;
			}
		}
 		
        return prepareFinalString(arrInput, flag);
    }
	
	private String prepareFinalString(char arrInput[], boolean flag) {
		StringBuffer sb = new StringBuffer();
		if (flag) {
			for (int i = startIndex; i <= endIndex; i++) {
				sb.append(arrInput[i]);
			}
		}
		return sb.toString();
	}

	class ListNode {
		private ListNode next;
		private ListNode previous;
		char ch;
		int index;
		
		public ListNode(Character ch, int index) {
			this.ch = ch;
			this.index = index;
		}
	}
	
}
