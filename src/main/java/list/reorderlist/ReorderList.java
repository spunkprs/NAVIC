package list.reorderlist;


/**
 Given the head of a singly linked list, reorder the list as if it were folded on itself.
 For example, if the list is represented as follows:

 L0 → L1 → L2 → … → L(n−2) → L(n-1) → L(n)

 This is how you’ll reorder it:

 L0 → L(n) → L2 → … → L(n−1) → L2 → L(n-2) -> ...


 You don’t need to modify the values in the list’s nodes; only the links between nodes need to be changed

 Credits --> Educative

 * */

public class ReorderList {

    public static void main(String ar[]) {
        ReorderList unit = new ReorderList();

        ListNode nodeOne = new ListNode(1);
        ListNode nodeTwo = new ListNode(2);
        ListNode nodeThree = new ListNode(3);
        ListNode nodeFour = new ListNode(4);
        ListNode nodeFive = new ListNode(5);

        nodeOne.next = nodeTwo;
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeFour;
        nodeFour.next = nodeFive;

        ListNode result = unit.reorderList(nodeOne);

        System.out.println("List post reordering is :: " + result);
    }

    public ListNode reorderList(ListNode head) {

        int lengthOfList = lengthOfTheList(head);

        if (lengthOfList >= 1 && lengthOfList <= 2) {
            return head;
        } else if (lengthOfList == 3) {
            ListNode headNode = head;
            ListNode headNodeNext = headNode.next;
            ListNode tailNode = headNodeNext.next;

            tailNode.next = headNodeNext;
            headNodeNext.next = null;
            headNode.next = tailNode;
            return headNode;
        } else {
            return processToReorderList(head, lengthOfList);
        }
    }

    private ListNode processToReorderList(ListNode head, int listLength) {
        boolean isSizeOdd = listLength % 2 != 0 ? true : false;

        ListNode midNode = findMidNode(head, listLength, isSizeOdd);

        ListNode reversedListNode = reverseList(midNode.next);

        midNode.next = null;

        ListNode headNodeOne = head;
        ListNode headNodeTwo = reversedListNode;

        int lengthToBeTravelled = listLength / 2;

        int counter = 1;

        while (counter <= lengthToBeTravelled) {
            ListNode headNodeOneNext = headNodeOne.next;
            ListNode headNodeTwoNext = headNodeTwo.next;

            headNodeOne.next = headNodeTwo;

            headNodeOne = headNodeOneNext;

            headNodeTwo.next = headNodeOne;

            headNodeTwo = headNodeTwoNext;
            counter++;
        }
        return head;
    }

    private ListNode findMidNode(ListNode head, int listLength, boolean isSizeOdd) {
        ListNode node = head;
        int mid = 0;
        int lengthTravelled = 1;
        if (isSizeOdd) {
           mid = listLength / 2 + 1;
        } else {
            mid = listLength / 2;
        }

        while (lengthTravelled < mid) {
            node = node.next;
            lengthTravelled++;
        }
        return node;
    }


    private ListNode reverseList(ListNode headNode) {
        ListNode node = headNode;

        ListNode prev = node;
        ListNode curr = prev.next;
        ListNode currNext = null;

        while (curr != null) {
            currNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = currNext;
        }
        node.next = null;
        return prev;
    }


    private int lengthOfTheList(ListNode head) {
        int length = 0;
        ListNode node = head;

        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
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
