package leetcode75;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list
 is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

 For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2.
 These are the only nodes with twins for n = 4.
 The twin sum is defined as the sum of a node and its twin.

 Given the head of a linked list with even length, return the maximum twin sum of the linked list

Constraints:

a.) The number of nodes in the list is an even integer in the range [2, pow(10,5)].
b.) 1 <= Node.val <= pow(10,5)

Time Complexity = O(n) --> Iterating every node in the list
Space Complexity = O(n) --> Making use of both stack && queue that will possess half of nodes each of the list
 * */

public class MaximumTwinSumOfLinkedList {

    public int pairSum(ListNode head) {

        if (head.next.next == null) {
            return head.val + head.next.val;
        } else {
            int length = fetchLengthOfList(head);
            Queue<Integer> queue = new LinkedList();
            Stack<Integer> stack = new Stack();

            int index = 0;

            ListNode node = head;

            while (node != null) {
                if (index <= (length/2) - 1) {
                    queue.add(node.val);
                } else {
                    stack.add(node.val);
                }
                node = node.next;
                index++;
            }

            int resultSum = 0;
            while (!stack.isEmpty()) {
                int sum = stack.peek() + queue.peek();
                resultSum = sum > resultSum ? sum : resultSum;
                stack.pop();
                queue.poll();
            }
            return resultSum;
        }

    }

    private int fetchLengthOfList(ListNode node) {
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
