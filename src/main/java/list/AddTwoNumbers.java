package list;

import java.util.Stack;

public class AddTwoNumbers {
	
	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
		public void setVal(int val) {
			this.val = val;
		}
		public void setNext(ListNode next) {
			this.next = next;
		}
	 }

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return performAddition(l1, l2);
    }
	
	public ListNode addTwoNumbersCaseTwo(ListNode l1, ListNode l2) {
		ListNode result = null;
		int carry = 0;
		Stack<ListNode> stackOne = prepareStack(l1);
		Stack<ListNode> stackTwo = prepareStack(l2);
		while (!stackOne.isEmpty() && !stackTwo.isEmpty()) {
			int numOne = stackOne.pop().val;
			int numTwo = stackTwo.pop().val;
			if ((numOne + numTwo + carry) / 10 != 0) {
				if (result == null) {
					result = new ListNode((numOne + numTwo + carry) % 10);
				} else {
					 ListNode nextNode = new ListNode((numOne + numTwo + carry) % 10);
					 nextNode.next = result;
					 result = nextNode;
				}
			} else {
				if (result == null) {
					result = new ListNode((numOne + numTwo + carry) % 10);
				} else {
					 ListNode nextNode = new ListNode((numOne + numTwo + carry) % 10);
					 nextNode.next = result;
					 result = nextNode;
				}
			}
			carry = (numOne + numTwo + carry) / 10;
		}
		
		while(!stackOne.isEmpty()) {
			int numOne = stackOne.pop().val;
			if ((numOne + carry)/10 != 0) {
				if (result == null) {
					result = new ListNode((numOne + carry) % 10);
				} else {
					 ListNode nextNode = new ListNode((numOne + carry) % 10);
					 nextNode.next = result;
					 result = nextNode;
				}
			} else {
				if (result == null) {
					result = new ListNode((numOne + carry) % 10);
				} else {
					 ListNode nextNode = new ListNode((numOne + carry) % 10);
					 nextNode.next = result;
					 result = nextNode;
				}
			}
			carry = (numOne + carry) / 10;
		}
		
		while(!stackTwo.isEmpty()) {
			int numTwo = stackTwo.pop().val;
			if ((numTwo + carry)/10 != 0) {
				if (result == null) {
					result = new ListNode((numTwo + carry) % 10);
				} else {
					 ListNode nextNode = new ListNode((numTwo + carry) % 10);
					 nextNode.next = result;
					 result = nextNode;
				}
			} else {
				if (result == null) {
					result = new ListNode((numTwo + carry) % 10);
				} else {
					 ListNode nextNode = new ListNode((numTwo + carry) % 10);
					 nextNode.next = result;
					 result = nextNode;
				}
			}
			carry = (numTwo + carry) / 10;
		}
		
		if (carry > 0) {
			ListNode nextNode = new ListNode(carry);
			nextNode.next = result;
			result = nextNode;
			carry = 0;
		}
		
        return result;
    }
	
	private Stack<ListNode> prepareStack(ListNode node) {
		Stack<ListNode> stack = new Stack<ListNode>();
		while (node != null) {
			stack.push(node);
			node = node.next;
		}
		return stack;
	}

	private ListNode performAddition(ListNode l1, ListNode l2) {
		ListNode headOne = l1;
		ListNode headTwo = l2;
		ListNode result = null;
		ListNode finalResult = null;
		int carry = 0;
		while (headOne != null && headTwo != null) {
			if (headOne.val + headTwo.val + carry < 10) {
				if (result == null) {
					result = new ListNode(headOne.val + headTwo.val);
					finalResult = result;
				} else {
					 	int r = headOne.val + headTwo.val + carry;
						ListNode next = new ListNode(r % 10);
						carry = r / 10;
						result.next = next;
						result = next;
				}
			} else {
				if (result == null) {
					result = new ListNode((headOne.val + headTwo.val) % 10);
					carry = (headOne.val + headTwo.val) / 10;
					finalResult = result;
				} else {
					int r = headOne.val + headTwo.val + carry;
					ListNode next = new ListNode(r % 10);
					carry = r / 10;
					result.next = next;
					result = next;
				}
			}
			headOne = headOne.next;
			headTwo = headTwo.next;
		}
		
		while (headOne != null) {
			if (carry > 0) {
				if (carry + headOne.val >= 10) {
					ListNode next = new ListNode((carry + headOne.val) % 10);
					result.next = next;
					result = next;
					
					carry = (carry + headOne.val) / 10;
				} else {
					ListNode next = new ListNode(carry + headOne.val);
					result.next = next;
					result = next;
					carry = 0;
				}
				
			} else {
				ListNode next = new ListNode(headOne.val);
				result.next = next;
				result = next;
			} 
			
			headOne = headOne.next;
		}
		
		while (headTwo != null) {
			if (carry > 0) {
				if (carry + headTwo.val >= 10) {
					ListNode next = new ListNode((carry + headTwo.val) % 10);
					result.next = next;
					result = next;
					
					carry = (carry + headTwo.val) / 10;
				} else {
					ListNode next = new ListNode(carry + headTwo.val);
					result.next = next;
					result = next;
					carry = 0;
				}
			} else {
				ListNode next = new ListNode(headTwo.val);
				result.next = next;
				result = next;
			}
			headTwo = headTwo.next;
		}
		
		if (carry > 0) {
			ListNode next = new ListNode(carry);
			result.next = next;
			result = next;
			carry = 0;
		}
		return finalResult;
	}

	private ListNode reverseList(ListNode listNode) {
		ListNode head = listNode;
		ListNode nodeOne = listNode;
		ListNode nodeTwo = null;
		
		if (nodeOne != null && nodeOne.next != null) {
			nodeTwo = nodeOne.next;
		}
		
		
		while (nodeOne != null && nodeTwo != null) {
			ListNode next = nodeTwo.next;
			nodeTwo.next = nodeOne;
			nodeOne = nodeTwo;
			nodeTwo = next;
		}
		head.next = null;
		return nodeOne;
	}

	public String appendNumbers(ListNode result) {
		StringBuffer sb = new StringBuffer();
		while (result != null) {
			sb.append(result.val);
			result = result.next;
		}
		return sb.toString();
	}
}

	 
