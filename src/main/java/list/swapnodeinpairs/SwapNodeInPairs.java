package list.swapnodeinpairs;

/**
 Given a singly linked list, swap every two adjacent nodes of the linked list. After the swap, return the head of the linked list

 Constraints:-
 1.) The number of nodes in the list is in the range --> [0,100]
 2.) 0 ≤ Node.value ≤ 100

 Credits --> Educative

 Time Complexity --> O(n)
 Space Complexity --> O(1)

 Hence it's an in place solution for the problem guaranteeing no extra space used for coming up with the solution
 * */

public class SwapNodeInPairs {

    public static void main(String ar[]) {

    SwapNodeInPairs unit = new SwapNodeInPairs();

    ListNode nodeOne = new ListNode(9);
    ListNode nodeTwo = new ListNode(0);
    ListNode nodeThree = new ListNode(8);
    ListNode nodeFour = new ListNode(2);

    nodeOne.next = nodeTwo;
    nodeTwo.next = nodeThree;
    nodeThree.next = nodeFour;

    ListNode head = unit.swapPairs(nodeOne);

    System.out.println("Restructured linked list post swapping adjacent nodes is " + head);
    }

    public ListNode swapPairs(ListNode head) {
        /**
         To handle the scenario when either list is empty or has single node
         * */
        if (head == null || head.next == null) {
            return head;
        } else {
            return processToSwapPairs(head);
        }
    }

    /**
     Manipulation of pointers guaranteed no extra space used, following are the major pointers used for the manipulation -->
     a.) prev
     b.) curr
     c.) currNext
     d.) tmp

     Rest the code is self explanatory for getting it's being used internally to build the logic
     * */
    private ListNode processToSwapPairs(ListNode node) {
        ListNode prev = node;
        ListNode head = null;
        ListNode tmp = null;

        while (prev != null && prev.next != null) {
            ListNode curr = prev.next;
            ListNode currNext = curr.next;
            prev.next = currNext;
            curr.next = prev;
            if (tmp != null) {
                tmp.next = curr;
                tmp = prev;
            } else {
                tmp = prev;
            }
            if (prev == node) {
                head = curr;
            }
            prev = currNext;
        }
        return head;
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
