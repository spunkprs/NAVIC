package list.swapnodeinpairs;

/**
 Given a singly linked list, swap every two adjacent nodes of the linked list. After the swap, return the head of the linked list

 Constraints:-
 1.) The number of nodes in the list is in the range --> [0,100]
 2.) 0 ≤ Node.value ≤ 100

 * */

public class SwapNodeInPairs {

    public static void main(String ar[]) {

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
