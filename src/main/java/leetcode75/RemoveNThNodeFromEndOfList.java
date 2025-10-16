package leetcode75;

/**
 Given the head of a linked list, remove the nth node from the end of the list and return its head

Constraints:-

a.) The number of nodes in the list is sz.
b.) 1 <= sz <= 30
c.) 0 <= Node.val <= 100
d.) 1 <= n <= sz


Time Complexity = O(n) --> Iterating every node in the list
Space Complexity = O(1) --> Chose iterative approach for reversing nodes instead of recursive

Source : LeetCode
Level : Medium
 * */

public class RemoveNThNodeFromEndOfList {

    public static void main(String ar[]) {
        RemoveNThNodeFromEndOfList unit = new RemoveNThNodeFromEndOfList();
        ListNode nodeOne = new ListNode(1);
        ListNode nodeTwo = new ListNode(2);
        ListNode nodeThree = new ListNode(3);
        ListNode nodeFour = new ListNode(4);
        ListNode nodeFive = new ListNode(5);

        nodeOne.next = nodeTwo;
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeFour;
        nodeFour.next = nodeFive;

        unit.removeNthFromEnd(nodeOne, 2);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int lengthOfList = findLengthOfList(head);

        if (lengthOfList - n == 0) {
            return head.next;
        } else {
            ListNode prev = null;
            int step = lengthOfList - n + 1;
            ListNode nodeOne = head;

            int index = 1;
            while (index < step) {
                if (prev == null) {
                    prev = nodeOne;
                } else {
                    prev = prev.next;
                }
                index++;
                nodeOne = nodeOne.next;
            }

            prev.next = nodeOne.next;
        }
        return head;
    }

    private int findLengthOfList(ListNode node) {
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
        public ListNode(int x) {
            this.val = x;
        }
    }
}
