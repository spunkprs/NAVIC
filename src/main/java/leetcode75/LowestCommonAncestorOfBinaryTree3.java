package leetcode75;

import java.util.Stack;

/**
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

 class Node {
 public int val;
 public Node left;
 public Node right;
 public Node parent;
 }

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T
is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)

Constraints:-

a.) The number of nodes in the tree is in the range [2, 10^5].
b.) -10^9 <= Node.val <= 10^9
c.) All Node.val are unique.
d.) p != q
e.) p and q exist in the tree
 * */

public class LowestCommonAncestorOfBinaryTree3 {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {

        Stack<Node> stackOne = prepareStack(p);
        Stack<Node> stackTwo = prepareStack(q);

        Node prev = null;
        while (!stackOne.isEmpty() && !stackTwo.isEmpty() && stackOne.peek() == stackTwo.peek()) {
            prev = stackOne.pop();
            stackTwo.pop();
        }
        return prev;
    }

    private Stack<Node> prepareStack(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.parent;
        }
        return stack;
    }
}
