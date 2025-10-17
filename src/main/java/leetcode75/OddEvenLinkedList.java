package leetcode75;

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
