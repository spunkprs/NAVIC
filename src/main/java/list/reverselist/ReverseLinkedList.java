package list.reverselist;

/**
 Given a singly linked list with n nodes and two positions, left and right, the objective is to reverse the nodes of the list
 from left to right. Return the modified list

 Constraints:

 a.) 1 ≤ n ≤ 500
 b.) −5000 ≤ node.val ≤ 5000
 c.) 1 ≤ left ≤ right ≤ n

 Credits --> Educative
 References --> https://www.educative.io/interview-prep/coding/reverse-linked-list-ii

 Time Complexity --> O(n)
 Space Complexity --> O(1)

 Hence it's an in place solution for the problem guaranteeing no extra space used for coming up with the solution
 * */

public class ReverseLinkedList {


    public static void main(String ar[]) {

        ReverseLinkedList unit = new ReverseLinkedList();

        ListNode nodeOne = new ListNode(9);
        ListNode nodeTwo = new ListNode(0);
        ListNode nodeThree = new ListNode(8);
        ListNode nodeFour = new ListNode(2);

        nodeOne.next = nodeTwo;
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeFour;

        int left = 2;
        int right = 3;

        ListNode head = unit.reverseBetween(nodeOne, left, right);
        System.out.println("Restructured linked list post swapping adjacent nodes is " + head);
    }

    public ListNode reverseBetween(ListNode head, int left, int right)
    {
        if (left == right) {
            return head;
        } else if (head.next == null) {
            return head;
        }
        return processToReverseListInRange(head, left, right);
    }

    private ListNode processToReverseListInRange(ListNode node, int left, int right) {
        ListNode previousNode = fetchNodeAsPerPosition(node, left - 1, true);
        ListNode rightNode = fetchNodeAsPerPosition(node, right, false);

        if (previousNode != null) {
            ListNode leftNode = previousNode.next;
            ListNode fetchedNode = processToReverseList(leftNode, rightNode);
            previousNode.next = fetchedNode;
            return node;
        } else {
            ListNode leftNode = node;
            ListNode fetchedNode = processToReverseList(leftNode, rightNode);
            return fetchedNode;
        }
    }

    private ListNode processToReverseList(ListNode leftNode, ListNode rightNode) {
        ListNode rightNodeNext = rightNode.next;
        ListNode prevNode = leftNode;
        ListNode temp = prevNode;
        ListNode currNode = leftNode.next;
        ListNode currNextNode = null;

        while (currNode != rightNodeNext) {
            currNextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = currNextNode;
        }
        temp.next = rightNodeNext;
        return prevNode;
    }

    private ListNode fetchNodeAsPerPosition(ListNode node, int position, boolean isLeft) {
        int count = 1;
        if (isLeft) {
            if (position < count) {
                return null;
            } else {
                while (count < position) {
                    node = node.next;
                    count++;
                }
                return node;
            }
        } else {
            while (count < position) {
                node = node.next;
                count++;
            }
            return node;
        }
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
