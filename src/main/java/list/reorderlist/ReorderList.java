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
            processToReorderList(head, lengthOfList);
        }

        return head;
    }

    private void processToReorderList(ListNode head, int listLength) {

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
