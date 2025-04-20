package list.slowandfastpointer;

/*
*
Given the head of a linked list, determine the length of the cycle present in the linked list. If there is no cycle, return 0.
A cycle exists in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.

Constraints:
1.) The number of nodes in the list is in the range --> [0,pow(10,4)]
2.) pow(−10, 5) ≤ Node.value ≤ pow(10, 5)
*
* */
public class LinkedListCycle {

    public static void main(String ar[]) {
        LinkedListCycle unit = new LinkedListCycle();

        ListNode nodeOne = new ListNode(3);
        ListNode nodeTwo = new ListNode(7);
        ListNode nodeThree = new ListNode(9);
        ListNode nodeFour = new ListNode(6);
        ListNode nodeFive = new ListNode(8);

        nodeOne.next = nodeTwo;
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeFour;
        nodeFour.next = nodeFive;

        nodeFive.next = nodeThree;

        System.out.print("Length of the cycle against the linked list :: " + unit.countCycleLength(nodeOne));
    }

    /*
    * Following cases are possible as part of this problem :-
    * Case 1 : When cycle does exists in the linked list && the length of the cycle > 1
    * Case 2 : When cycle does exists in the linked list && the length of the cycle = 1 && length of the list is also 1
    * Case 3 : When cycle does exists in the linked list && the length of the cycle = 1 && length of the list is > 1
    * Case 4 : No cycle in the linked list
    * Case 5 : Linked list is empty
    * */

    public int countCycleLength(ListNode head) {
        if (head != null) {
            if (head == head.next) {
                return 1;
            } else {
                ListNode meetingPointNode = findNodeAtWhichTwoPointersMet(head);
                if (meetingPointNode != null) {
                    return findLengthOfTheCycle(meetingPointNode);
                }
                return 0;
            }
        }
        return 0;
    }

    private int findLengthOfTheCycle(ListNode meetingPointNode) {
        ListNode listNode = meetingPointNode.next;
        int count = 1;

        while (listNode != meetingPointNode) {
            count++;
            listNode = listNode.next;
        }
        return count;
    }

    private ListNode findNodeAtWhichTwoPointersMet(ListNode node) {
        ListNode result = null;
        boolean cycleExists = true;
        ListNode sp = node;
        ListNode fp = node;

        ListNode fpIntermittent = fp.next;
        ListNode fpNext = fpIntermittent.next;

        if (fpIntermittent == null || fpNext == null) {
            return result;
        }

        sp = sp.next;
        fp = fpNext;

        while (sp != fp) {
            if (fp.next == null || fp.next.next == null) {
                cycleExists = false;
                break;
            }

            fpIntermittent = fp.next;
            fpNext = fpIntermittent.next;

            sp = sp.next;
            fp = fpNext;
        }

        if (cycleExists) {
            result = sp;
        }
        return result;
    }

    static class ListNode {
     private int val;
     private ListNode next;

     public ListNode(int val) {
         this.val = val;
         this.next = null;
     }
}
}
