package binarytree;

public class ExpressionTree {

    public int evalTree(Node root) {
        return evaluateExpression(root);
    }

    private int evaluateExpression(Node node) {
        Node leftNode = node.left;
        Node rightNode = node.right;

        if (leftNode == null && rightNode == null) {
            return Integer.parseInt(node.data);
        } else {
            if (node.data.equals("+")) {
                return evaluateExpression(leftNode) + evaluateExpression(rightNode);
            } else if (node.data.equals("-")) {
                return evaluateExpression(leftNode) - evaluateExpression(rightNode);
            } else if (node.data.equals("*")) {
                return evaluateExpression(leftNode) * evaluateExpression(rightNode);
            } else {
                return evaluateExpression(leftNode) / evaluateExpression(rightNode);
            }
        }
    }


    public static class Node {
        private String data;
        private Node left,right;
        public Node(String data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
