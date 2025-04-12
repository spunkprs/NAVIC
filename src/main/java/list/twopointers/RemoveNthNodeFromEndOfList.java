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

        //Adding test case
        ListNode listNodeOne = new ListNode(23);
        ListNode listNodeTwo = new ListNode(28);
        ListNode listNodeThree = new ListNode(10);
        ListNode listNodeFour = new ListNode(5);
        ListNode listNodeFive = new ListNode(67);
        ListNode listNodeSix = new ListNode(39);
        ListNode listNodeSeven = new ListNode(70);
        ListNode listNodeEight = new ListNode(28);

        listNodeOne.next = listNodeTwo;
        listNodeTwo.next = listNodeThree;
        listNodeThree.next = listNodeFour;
        listNodeFour.next = listNodeFive;
        listNodeFive.next = listNodeSix;
        listNodeSix.next = listNodeSeven;
        listNodeSeven.next = listNodeEight;

        unit.removeNthLastNode(listNodeOne, 2);
    }


    /*
     * Following cases needs to be covered :-
     * 1.) Node to be removed is the head node
     * 2.) Node to be removed is the tail node
     * 3.) Node to be removed is intermediate node
     * 4.) When length of the list is 1
     * */
    public ListNode removeNthLastNode(ListNode node, int n) {
        ListNode head = new ListNode(-100);
        head.next = node;
        return removeNode(head, n);
    }

    private ListNode removeNode(ListNode head, int n) {
        ListNode fastPointer = head;
        ListNode slowPointer = head;

        int count = 1;
        while (count <= n) {
            fastPointer = fastPointer.next;
            count++;
        }

        while (fastPointer.next != null) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }

        if (slowPointer.next != fastPointer) {
            ListNode temp = slowPointer.next;
            slowPointer.next = temp.next;
        } else {
            slowPointer.next = null;
        }
        return head.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
        this.val = val;
        this.next = null;
        }
        }
}
