package list;

public class ReorderList {
	
	private ListNode currentNode = null;
	private int lengthTillNow = 1;
	private boolean flag = false;
	
	public void reorderList(ListNode head) {
        int lengthOfList = findLengthOfList(head);
        
        ListNode node = head;
        
        if (lengthOfList > 2) {
        	currentNode = node;
        	processToReorder(lengthOfList, node);
        }
    }

	private void processToReorder(int lengthOfList, ListNode node) {
		if (node != null) {
			processToReorder(lengthOfList, node.getNext());
			if (currentNode.getNext() != null && lengthTillNow <= lengthOfList / 2 && !flag) {
				ListNode nextNode = currentNode.getNext();
				currentNode.setNext(node);
				currentNode = nextNode;
				node.setNext(currentNode);
				if (lengthTillNow == lengthOfList / 2 && !flag) {
					if (lengthOfList % 2 == 0) {
						node.setNext(null);
					} else {
						currentNode.setNext(null);
					}
					flag = true;
					return;
				}
				lengthTillNow++;
			}
		}
	}

	private int findLengthOfList(ListNode node) {
		int length = 0;
		while (node != null) {
			length++;
			node = node.getNext();
		}	
		return length;
	}

}
