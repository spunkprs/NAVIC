package leetcode75;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
