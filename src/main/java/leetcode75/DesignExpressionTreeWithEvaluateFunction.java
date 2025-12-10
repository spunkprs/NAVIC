package leetcode75;

import java.util.Stack;

/**
Problem : 1628
 Given the postfix tokens of an arithmetic expression, build and return the binary expression tree that represents this expression.

 Postfix notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before their operators. For example,
 the postfix tokens of the expression 4*(5-(7+2)) are represented in the array postfix = ["4","5","7","2","+","-","*"].

 The class Node is an interface you should use to implement the binary expression tree.
 The returned tree will be tested using the evaluate function, which is supposed to evaluate the tree's value.
 You should not remove the Node class; however, you can modify it as you wish, and you can define other classes
 to implement it if needed.

 A binary expression tree is a kind of binary tree used to represent arithmetic expressions.
 Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children)
 correspond to operands (numbers), and internal nodes (nodes with two children)
 correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).

 It's guaranteed that no subtree will yield a value that exceeds 10^9 in absolute value, and all the operations
 are valid (i.e., no division by zero).

 Follow up: Could you design the expression tree such that it is more modular? For example,
 is your design able to support additional operators without making changes to your existing evaluate implementation?

 Follow Up can be taken care of by introducing putting all the operators inside an ENUM instead and all the checks
 shall validate the presence of operator inside the ENUM, addition && removal of operator will be convenient now


 Constraints:-

a.) 1 <= s.length < 100
b.) s.length is odd.
c.) s consists of numbers and the characters '+', '-', '*', and '/'.
d.) If s[i] is a number, its integer representation is no more than 10^5.
e.) It is guaranteed that s is a valid expression.
f.) The absolute value of the result and intermediate values will not exceed 10^9.
g.) It is guaranteed that no expression will include division by zero.

 Reference : https://en.wikipedia.org/wiki/Binary_expression_tree

 Time Complexity : O(N)
 Space Complexity : O(N), where N being number of operators + number of operands

 * */

public class DesignExpressionTreeWithEvaluateFunction {


    public static void main(String ar[]) {
        DesignExpressionTreeWithEvaluateFunction unit = new DesignExpressionTreeWithEvaluateFunction();
        //String arr[] = {"3", "4", "+", "2", "*", "7", "/"};
        String arr[] = {"4","5","2","7","+","-","*"};
        Node result = unit.buildTree(arr);

        System.out.println("Result of the expression tree is " + result.evaluate());
    }

    abstract static class Node {

        public Node(String val) {
            this.val = val;
        }

        public abstract int evaluate();
        private String val;

        public String getVal() {
            return val;
        }

        public abstract Node fetchLeftNode();
        public abstract Node fetchRightNode();
        public abstract boolean isLeafNode();
    }

    static class TreeNode extends Node {

        public TreeNode(String val) {
            super(val);
        }

        @Override
        public int evaluate() {

            if (this.isLeafNode()) {
                return Integer.parseInt(super.getVal());
            }

            int leftSubTreeResult = processNode(this.fetchLeftNode());
            int rightSubTreeResult = processNode(this.fetchRightNode());
            if (getVal().equals("+")) {
                return leftSubTreeResult + rightSubTreeResult;
            } else if (getVal().equals("-")) {
                return leftSubTreeResult - rightSubTreeResult;
            } else if (getVal().equals("*")) {
                return leftSubTreeResult * rightSubTreeResult;
            } else {
                return leftSubTreeResult / rightSubTreeResult;
            }
        }

        @Override
        public Node fetchLeftNode() {
            return this.leftNode;
        }

        @Override
        public Node fetchRightNode() {
            return this.rightNode;
        }

        private int processNode(Node node) {
            return node.evaluate();
        }

        private Node leftNode;
        private Node rightNode;

        public boolean isLeafNode() {
            String val = super.getVal();
            if (val.equals("+") || val.equals("-") || val.equals("*") || val.equals("/")) {
                return false;
            }
            return true;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
    }

    public Node buildTree(String[] postfix) {
        Node rootNode = processToBuildExpressionTree(postfix);
        return rootNode;
    }

    private Node processToBuildExpressionTree(String[] postfix) {
        Stack<Node> stack = new Stack<>();
        int index = 0;
        while (index < postfix.length) {
            String val = postfix[index];
            if (val.equals("+") || val.equals("-") || val.equals("*") || val.equals("/")) {
                Node rightNode = stack.pop();
                Node leftNode = stack.pop();
                TreeNode node = new TreeNode(val);

                node.setRightNode(rightNode);
                node.setLeftNode(leftNode);
                stack.push(node);
            } else {
                Node node = new TreeNode(val);
                stack.push(node);
            }
            index++;
        }
        return stack.pop();
    }

}
