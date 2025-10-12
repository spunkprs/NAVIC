package leetcode75;

/**
You are given the head of a linked list. Delete the middle node,
and return the head of the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node
from the start using 0-based indexing, where ⌊x⌋ denotes the largest
integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.

Constraints:-

a.) The number of nodes in the list is in the range [1, pow(10,5)].
b.) 1 <= Node.val <= pow(10,5)

 * */

public class DeleteMiddleElementOfLinkedList {

    public static void main(String ar[]) {
        DeleteMiddleElementOfLinkedList unit = new DeleteMiddleElementOfLinkedList();

    }

    public ListNode deleteMiddle(ListNode head) {

        if (head.next == null) {
            return null;
        } else {
            int length = fetchLength(head);

            ListNode prev = null;
            ListNode traverseNode = head;

            int middleElement = length/2;

            int index = 0;

            while (index < middleElement) {
                index++;

                if (index > 0) {
                    if (prev == null) {
                        prev = traverseNode;
                    } else {
                        prev = prev.next;
                    }
                }

                traverseNode = traverseNode.next;
            }

            prev.next = traverseNode.next;
            return head;
        }

    }

    private int fetchLength(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    static class ListNode {
       private int val;
       private ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
