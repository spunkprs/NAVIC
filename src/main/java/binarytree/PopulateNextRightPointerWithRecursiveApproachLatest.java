package binarytree;


/*
* This problem aims to connect tree nodes at each level using extra pointer{next} present in Binary tree Node
Here are the salient features of the approach used :-
1.) Made use of DFS instead of BFS as extra usage of memory was not allowed
2.) Iterating over the tree twice to make next node in the Binary tree node point to it's immediate right node{present on the same level}
3.) Time complexity would still be O(n) && space complexity would be O(1) [memory used during recursive process is not considered extra for this problem]
4.) Somehow leetcode is not accepting solution[41/55 test cases are getting passed]
* */
public class PopulateNextRightPointerWithRecursiveApproachLatest {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

    }

    public Node connect(Node root) {
        if (root != null) {
            connectNodesDuringTraversalOne(root);
            connectNodesDuringTraversalTwo(root);
            return root;
        } else {
            return null;
        }
    }

    private void connectNodesDuringTraversalOne(Node node) {
        Node lNode = node.left;
        Node rNode = node.right;

        if (lNode != null && rNode != null) {
            lNode.next = rNode;
        }

        if (lNode != null) {
            connectNodesDuringTraversalOne(lNode);
        }

        if (rNode != null) {
            connectNodesDuringTraversalOne(rNode);
        }
    }

    private void connectNodesDuringTraversalTwo(Node node) {
        Node temp = node;
        if (node.next != null && (node.left != null || node.right != null)) {
            while (temp.next != null && temp.next.left == null && temp.next.right == null) {
                temp = temp.next;
            }
            if (temp.next != null && (temp.next.left != null || temp.next.right != null)) {
                Node preferredNodeOne = node.right != null ? node.right : node.left;
                Node preferredNodeTwo = temp.next.left != null ? temp.next.left : temp.next.right;
                preferredNodeOne.next = preferredNodeTwo;
            }
        }

        Node lNode = node.left;
        if (lNode != null) {
            connectNodesDuringTraversalTwo(lNode);
        }

        Node rNode = node.right;
        if (rNode != null) {
            connectNodesDuringTraversalTwo(rNode);
        }
    }
}
