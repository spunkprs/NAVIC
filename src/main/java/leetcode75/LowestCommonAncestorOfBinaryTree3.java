package leetcode75;

import java.util.Stack;

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
