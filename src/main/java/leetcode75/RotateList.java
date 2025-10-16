package leetcode75;

/**
Given the head of a linked list, rotate the list to the right by k places

Constraints:-

a.) The number of nodes in the list is in the range [0, 500].
b.) -100 <= Node.val <= 100
c.) 0 <= k <= 2 * pow(10,9)

Time Complexity = O(n) --> Iterating every node in the list
Space Complexity = O(1) --> Chose iterative approach for reversing nodes instead of recursive

Source : LeetCode
Level : Medium
 * */

public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {

        if (head == null) {
            return head;
        } else {
            int length = findLengthOfList(head);

            if (length == k || k == 0 || length == 1) {
                return head;
            } else {
                int rotationNum = k > length ? length  - (k % length) : length - k;

                if (rotationNum == length) {
                    return head;
                }

                ListNode tempOne = head;
                ListNode prev = null;
                int indexOne = 1;
                ListNode tailStart = null;

                while (indexOne <= rotationNum) {
                    if (indexOne == 1) {
                        tailStart = tempOne;
                    }
                    indexOne++;
                    if (prev == null) {
                        prev = tempOne;
                    } else {
                        prev = prev.next;
                    }
                    tempOne = tempOne.next;
                }

                ListNode newHead = tempOne;

                while (tempOne.next != null) {
                    tempOne = tempOne.next;
                }

                tempOne.next = tailStart;
                prev.next = null;
                return newHead;
            }
        }
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
