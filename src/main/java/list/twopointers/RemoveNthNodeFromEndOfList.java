package list.twopointers;

/*
*
* Given the head of a singly linked list, remove the node from the end of the list and return its head.
*
* Constraints:
* The number of nodes in the list is k
* */

public class RemoveNthNodeFromEndOfList {

    public static void main(String ar[]) {
        RemoveNthNodeFromEndOfList unit = new RemoveNthNodeFromEndOfList();
    }

    public ListNode removeNthLastNode(ListNode head, int n) {

        int lengthOfTheList = getLengthOfTheList(head);
        int numberOfNodesToBeCovered = lengthOfTheList - n + 1;

        /*
        * Following cases needs to be covered :-
        * 1.) Node to be removed is the head node
        * 2.) Node to be removed is the tail node
        * 3.) Node to be removed is intermediate node
        * */
        return removeNode(head, numberOfNodesToBeCovered, lengthOfTheList);
    }

    private ListNode removeNode(ListNode head, int numberOfNodesToBeCovered, int lengthOfTheList) {
        ListNode node = head;
        if (numberOfNodesToBeCovered == 1) {
            if (node.next != null) {
                node = node.next;
                head = node;
            } else {
                head = null;
            }
        } else {
            int nodesToBeCovered = numberOfNodesToBeCovered - 1;
            int count = 1;
            while (count < nodesToBeCovered) {
                node = node.next;
                count++;
            }
            if (numberOfNodesToBeCovered == lengthOfTheList) {
                node.next = null;
            } else {
                ListNode nodeToBeRemoved = node.next;
                node.next = nodeToBeRemoved.next;
            }
        }
        return head;
    }

    private static int getLengthOfTheList(ListNode head) {
        ListNode node = head;
        int length = 1;
        while (node.next != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
        this.val = val;
        this.next = null;
        }
        }

}
