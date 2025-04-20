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

    /*
    * Following cases are possible as part of this problem :-
    * Case 1 : When cycle does exists in the linked list && the length of the cycle > 1
    * Case 2 : When cycle does exists in the linked list && the length of the cycle = 1 && length of the list is also 1
    * Case 3 : When cycle does exists in the linked list && the length of the cycle = 1 && length of the list is > 1
    * Case 4 : No cycle in the linked list
    * Case 5 : Linked list is empty
    * */

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
