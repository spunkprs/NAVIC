package list.reverselist;

/**
 Given a singly linked list with n nodes and two positions, left and right, the objective is to reverse the nodes of the list
 from left to right. Return the modified list

 Constraints:

 a.) 1 ≤ n ≤ 500
 b.) −5000 ≤ node.val ≤ 5000
 c.) 1 ≤ left ≤ right ≤ n

 * */

public class ReverseLinkedList {


    public static void main(String ar[]) {

        ReverseLinkedList unit = new ReverseLinkedList();

        ListNode nodeOne = new ListNode(9);
        ListNode nodeTwo = new ListNode(0);
        ListNode nodeThree = new ListNode(8);
        ListNode nodeFour = new ListNode(2);

        nodeOne.next = nodeTwo;
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeFour;

        int left = 2;
        int right = 4;

        ListNode head = unit.reverseBetween(nodeOne, left, right);
        System.out.println("Restructured linked list post swapping adjacent nodes is " + head);
    }

    public ListNode reverseBetween(ListNode head, int left, int right)
    {
        // Replace this placeholder return statement with your code
        return head;
    }

    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }
    }

}
