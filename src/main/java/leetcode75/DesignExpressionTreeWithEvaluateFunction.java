package leetcode75;

import java.util.Stack;

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
