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

    }

    public static int countCycleLength(ListNode head) {
        return 0;
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
