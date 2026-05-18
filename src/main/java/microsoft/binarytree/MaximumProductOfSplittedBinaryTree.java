package microsoft.binarytree;

/**
Problem : 1339

Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of
the sums of the subtrees is maximized.

Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.

Note that you need to maximize the answer before taking the mod and not after taking it.

Constraints:-

a.) The number of nodes in the tree is in the range [2, 5 * 104].
b.) 1 <= Node.val <= 104
 * */

public class MaximumProductOfSplittedBinaryTree {

    private long maxProduct = 1;

    public static void main(String ar[]) {
        MaximumProductOfSplittedBinaryTree unit = new MaximumProductOfSplittedBinaryTree();
        TreeNode rootNode = new TreeNode(1);
        TreeNode nodeOne = new TreeNode(2);
        TreeNode nodeTwo = new TreeNode(3);
        TreeNode nodeThree = new TreeNode(4);
        TreeNode nodeFour = new TreeNode(5);
        TreeNode nodeFive = new TreeNode(6);


        rootNode.right = nodeOne;

        nodeOne.left = nodeTwo;
        nodeOne.right = nodeThree;

        nodeThree.left = nodeFour;
        nodeThree.right = nodeFive;

        System.out.print("Maximum product of splitted binary tree is " + unit.maxProduct(rootNode));
    }

    public int maxProduct(TreeNode root) {
      Node rootNode = new Node(root.val);
      prepareIntermittentTree(root, rootNode);
      processToFetchMaxProduct(rootNode);
      int divisor = (int) (Math.pow(10, 9) + 7);
      return (int) (maxProduct % divisor);
    }

    private void processToFetchMaxProduct(Node rootNode) {
        process(rootNode, rootNode.sum);
    }

    private void process(Node node, long totalSum) {
        Node leftNode = node.left;
        Node rightNode = node.right;

        if (leftNode != null) {
            updateMaxProduct(totalSum - leftNode.sum, leftNode.sum);
            process(leftNode, totalSum);
        }

        if (rightNode != null) {
            updateMaxProduct(totalSum - rightNode.sum, rightNode.sum);
            process(rightNode, totalSum);
        }
    }

    private void updateMaxProduct(long a, long b) {
        maxProduct = a * b > maxProduct ? a * b : maxProduct;
    }

    private void prepareIntermittentTree(TreeNode treeNode, Node node) {
        TreeNode leftNode = treeNode.left;
        TreeNode rightNode = treeNode.right;
        Node lNode = null;
        Node rNode = null;

        if (leftNode != null) {
            lNode = new Node(leftNode.val);
            node.left = lNode;
            prepareIntermittentTree(leftNode, lNode);
        }

        if (rightNode != null) {
            rNode = new Node(rightNode.val);
            node.right = rNode;
            prepareIntermittentTree(rightNode, rNode);
        }

        if (lNode != null && rNode != null) {
            node.sum = lNode.sum + rNode.sum + node.val;
        } else if (lNode != null && rNode == null) {
            node.sum = lNode.sum + node.val;
        } else if (lNode == null && rNode != null) {
            node.sum = rNode.sum + node.val;
        } else {
            node.sum = node.val;
        }
    }


    static class TreeNode {
       private int val;
       private TreeNode left;
       private TreeNode right;
       TreeNode(int val) { this.val = val; }
    }

    static class Node {
        private long val;
        private long sum;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }
    }
}
