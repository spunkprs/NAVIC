package list;

public class MergeKSortedLists {
	
	public ListNode mergeKLists(ListNode[] lists) {
			if (lists.length >= 1) {
				return proceedRecursively(lists, 0, lists.length - 1);
			}
			return null;
	 }

	private ListNode proceedRecursively(ListNode[] lists, int start, int end) {
		if (end - start > 1) {
			int mid = (start + end) / 2;
			ListNode headOne = proceedRecursively(lists, start, mid);
			ListNode headTwo = proceedRecursively(lists, mid + 1, end);
			return mergeLists(headOne, headTwo);
		} else {
			if (end == start) {
				return lists[end];
			} else {
				return mergeLists(lists[start], lists[end]);
			}
		}
	}

	private ListNode mergeLists(ListNode headNodeOne, ListNode headNodeTwo) {
		ListNode headOne = headNodeOne;
		ListNode headTwo = headNodeTwo;
		ListNode previousNodeOne = null;
		
		if (headOne != null && headTwo != null) {
			while (headTwo.getNext() != null && headOne.getNext() != null) {
				if (headOne.getVal() >= headTwo.getVal()) {
					if (previousNodeOne == null) {
						previousNodeOne = headTwo;
						headTwo = headTwo.getNext();
						previousNodeOne.setNext(headOne);
						headNodeOne = previousNodeOne;
					} else {
						ListNode nodeTwo = headTwo;
						headTwo = headTwo.getNext();
						previousNodeOne.setNext(nodeTwo);
						nodeTwo.setNext(headOne);
						if (previousNodeOne != null) {
							previousNodeOne = previousNodeOne.getNext();
						}
					} 
				} else {
					if (previousNodeOne != null) {
						previousNodeOne = previousNodeOne.getNext();
					}
					if (previousNodeOne == null) {
						previousNodeOne = headOne;
					}
					headOne = headOne.getNext();
				}
				
			}
			
			if (headOne.getNext() == null && headTwo.getNext() != null) {
				while (headTwo != null) {
					if (headOne.getVal() >= headTwo.getVal()) {
						ListNode nodeTwo = headTwo;
						headTwo = headTwo.getNext();
						if (previousNodeOne != null) {
							previousNodeOne.setNext(nodeTwo);
							nodeTwo.setNext(headOne);
							previousNodeOne = previousNodeOne.getNext();
						} else {
							previousNodeOne = nodeTwo;
							nodeTwo.setNext(headOne);
							headNodeOne = previousNodeOne;
						}
					} else {
						headOne.setNext(headTwo);
						break;
					}
				}
			} else if (headOne.getNext() != null && headTwo.getNext() == null) {
				while (headOne != null) {
					if (headOne.getVal() >= headTwo.getVal()) {
						ListNode nodeTwo = headTwo;
						headTwo = headTwo.getNext();
						if (previousNodeOne != null) {
							previousNodeOne.setNext(nodeTwo);
							nodeTwo.setNext(headOne);
							previousNodeOne = previousNodeOne.getNext();
						} else {
							previousNodeOne = nodeTwo;
							nodeTwo.setNext(headOne);
							headNodeOne = previousNodeOne;
						}
						break;
					} else {
						if (previousNodeOne != null) {
							previousNodeOne = previousNodeOne.getNext();
						} else {
							previousNodeOne = headOne;
						}
						headOne = headOne.getNext();
					}
				}
				
				if (headOne == null && headTwo != null) {
					previousNodeOne.setNext(headTwo);
				}
				
			} else if (headOne.getNext() == null && headTwo.getNext() == null) {
				if (headOne.getVal() >= headTwo.getVal()) {
					if (previousNodeOne == null) {
						previousNodeOne = headTwo;
						headTwo = headTwo.getNext();
						previousNodeOne.setNext(headOne);
						headNodeOne = previousNodeOne;
					} else {
						ListNode nodeTwo = headTwo;
						headTwo = headTwo.getNext();
						previousNodeOne.setNext(nodeTwo);
						nodeTwo.setNext(headOne);
					}
				} else {
					headOne.setNext(headTwo);
				}
			}
			return headNodeOne;

		} else if (headOne != null && headTwo == null) {
			return headOne;
		} else if (headOne == null && headTwo != null) {
			return headTwo;
		}
		return null;
			}
}
