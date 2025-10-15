package leetcode75;


/**
Given the head of a singly linked list and two integers left and right
where left <= right, reverse the nodes of the list from position left to
position right, and return the reversed list.

Constraints:-
1.) The number of nodes in the list is n.
2.) 1 <= n <= 500
3.) -500 <= Node.val <= 500
4.) 1 <= left <= right <= n

Time Complexity = O(n) --> Iterating every node in the list
Space Complexity = O(1) --> Chose iterative approach for reversing nodes instead of recursive

Source : LeetCode
Level : Medium
 * */

public class ReverseLinkedList2 {

    private ListNode leftNode = null;
    private ListNode rightNode = null;
    private ListNode rightNodeNext = null;

    public static void main(String ar[]) {
        ReverseLinkedList2 unit = new ReverseLinkedList2();

        ListNode nodeOne = new ListNode(1);
        ListNode nodeTwo = new ListNode(2);

        nodeOne.next = nodeTwo;

        ListNode headNode = unit.reverseBetween(nodeOne, 1, 2);
        printList(headNode);
    }

    private static void printList(ListNode headNode) {
        ListNode node = headNode;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left != right) {
            return processToReverseList(head, left, right);
        }
        return head;
    }

    private ListNode processToReverseList(ListNode listNode, int left, int right) {
        int index = 0;
        ListNode prev = null;
        ListNode temp = listNode;
        ListNode head = listNode;

        while (listNode != null) {
            index++;

            if (leftNode == null && index == 2) {
                prev = temp;
            } else if (leftNode == null && index > 2) {
                prev = prev.next;
            }

            if (leftNode == null && index == left) {
                leftNode = listNode;
            }

            if (rightNode == null && index == right) {
                rightNode = listNode;
            }

            if (leftNode != null && rightNode != null) {
                processToReverseList();
                if (prev != null) {
                    prev.next = rightNode;
                }
            }

            if (leftNode != null && rightNode != null) {
                leftNode.next = rightNodeNext;
                break;
            } else {
                listNode = listNode.next;
            }
        }

        if (prev != null) {
            return head;
        }
        return rightNode;
    }

    private void processToReverseList() {
        ListNode tOne = leftNode;
        ListNode tTwo = leftNode.next;
        rightNodeNext = rightNode.next;

        while (tTwo != rightNode) {
            ListNode temp = tTwo.next;
            tTwo.next = tOne;
            tOne = tTwo;
            tTwo = temp;
        }

        tTwo.next = tOne;
    }

    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int x) {
            this.val = x;
        }
    }
}
