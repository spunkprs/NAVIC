package leetcode75;

import java.util.Stack;


/**
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself

Constraints:-

a.) The number of nodes in each linked list is in the range [1, 100].
b.) 0 <= Node.val <= 9
c.) It is guaranteed that the list represents a number that does not have leading zeros

Time Complexity = O(max(n1, n2))
Space Complexity = O(max(n1, n2))

Source : LeetCode
Level : Medium

 * */

public class AddTwoNumbers {

    private StringBuilder sbOne = new StringBuilder();

    public static void main(String ar[]) {
        AddTwoNumbers unit = new AddTwoNumbers();


        ListNode lOneNodeOne = new ListNode(0);

        ListNode lTwoNodeOne = new ListNode(2);
        ListNode lTwoNodeTwo = new ListNode(7);
        ListNode lTwoNodeThree = new ListNode(8);

        lTwoNodeOne.next = lTwoNodeTwo;
        lTwoNodeTwo.next = lTwoNodeThree;

        unit.addTwoNumbers(lOneNodeOne, lTwoNodeOne);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        fetchNumber(l1);
        String numOne = sbOne.append(l1.val).toString();
        sbOne = new StringBuilder();
        fetchNumber(l2);
        String numTwo = sbOne.append(l2.val).toString();

        String resultString = prepareSum(numOne, numTwo);

        char arr[] = resultString.toCharArray();

        ListNode head = null;
        ListNode tail = null;

        for (int i = arr.length - 1; i >= 0; i--) {
            ListNode node = new ListNode(Integer.parseInt(String.valueOf(arr[i])));
            if (head == null) {
                head = node;
                tail = head;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    private void fetchNumber(ListNode node) {
        ListNode nextNode = node.next;
        if (nextNode != null) {
            fetchNumber(nextNode);
            sbOne.append(nextNode.val);
        }
    }

    private String prepareSum(String numOne, String numTwo) {
        char arrOne[] = numOne.toCharArray();
        char arrTwo[] = numTwo.toCharArray();

        Stack<Integer> stack = new Stack();

        int indexOne = arrOne.length - 1;
        int indexTwo = arrTwo.length - 1;

        int carryForward = 0;

        while (indexOne >= 0 && indexTwo >= 0) {
            int digOne = Integer.parseInt(String.valueOf(arrOne[indexOne]));
            int digTwo = Integer.parseInt(String.valueOf(arrTwo[indexTwo]));

            int resultDigit = 0;
            if (digOne + digTwo + carryForward < 10) {
                resultDigit = digOne + digTwo + carryForward;
                stack.add(resultDigit);
                carryForward = 0;
            } else {
                resultDigit = (digOne + digTwo + carryForward) % 10;
                stack.add(resultDigit);
                carryForward = 1;
            }
            indexOne--;
            indexTwo--;
        }

        if (indexOne >= 0 && indexTwo < 0) {
            while (indexOne >= 0) {
                int digOne = Integer.parseInt(String.valueOf(arrOne[indexOne]));

                int resultDigit = 0;
                if (digOne + carryForward < 10) {
                    resultDigit = digOne + carryForward;
                    stack.add(resultDigit);
                    carryForward = 0;
                } else {
                    resultDigit = (digOne + carryForward) % 10;
                    stack.add(resultDigit);
                    carryForward = 1;
                }
                indexOne--;
            }
        } else if (indexTwo >= 0 && indexOne < 0) {
            while (indexTwo >= 0) {
                int digTwo = Integer.parseInt(String.valueOf(arrTwo[indexTwo]));

                int resultDigit = 0;
                if (digTwo + carryForward < 10) {
                    resultDigit = digTwo + carryForward;
                    stack.add(resultDigit);
                    carryForward = 0;
                } else {
                    resultDigit = (digTwo + carryForward) % 10;
                    stack.add(resultDigit);
                    carryForward = 1;
                }
                indexTwo--;
            }
        }

        if (carryForward > 0) {
            stack.add(carryForward);
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int x) {
            this.val = x;
        }
    }

}
