package leetcode75;

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
