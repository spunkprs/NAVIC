package leetcode75;


/**
Given the head of a singly linked list, group all the nodes with odd indices
together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should
remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.

Constraints:-

The number of nodes in the linked list is in the range [0, pow(10,4)].
-pow(10,6) <= Node.val <= pow(10,6)

Time Complexity = O(n) --> Iterating every node in the list
Space Complexity = O(1)

Source : LeetCode
Level : Medium
 * */

public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {

        if (head == null) {
            return head;
        } else if (head.next == null) {
            return head;
        } else if (head.next.next == null) {
            return head;
        } else {
            ListNode headOne = head;
            ListNode headTwo = head.next;

            ListNode tOne = headOne;
            ListNode tTwo = headTwo;

            boolean flag = true;

            while (tTwo != null && tTwo.next != null) {
                if (flag) {
                    ListNode nextNode = tTwo.next;
                    tOne.next = nextNode;
                    tOne = tOne.next;
                    flag = false;
                } else {
                    ListNode nextNode = tOne.next;
                    tTwo.next = nextNode;
                    tTwo = tTwo.next;
                    flag = true;
                }
            }

            tOne.next = headTwo;
            return headOne;
        }

    }

    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int x) {
            this.val = x;
        }
    }
}
