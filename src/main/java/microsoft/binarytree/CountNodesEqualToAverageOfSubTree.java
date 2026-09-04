package microsoft.binarytree;


/**
Problem : 2265
Level : Medium
Link : https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/description/?envType=problem-list-v2&envId=binary-tree

Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:-

The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.

Constraints:-

a.) The number of nodes in the tree is in the range [1, 1000].
b.) 0 <= Node.val <= 1000

Time Complexity : O(N)
Space Complexity : O(N)

Where N being number of nodes in th tree
 * */

public class CountNodesEqualToAverageOfSubTree {

    public static void main(String ar[]) {
        CountNodesEqualToAverageOfSubTree unit = new CountNodesEqualToAverageOfSubTree();

        TreeNode root = new TreeNode(4);
        TreeNode nodeOne = new TreeNode(8);
        TreeNode nodeTwo = new TreeNode(5);
        TreeNode nodeThree = new TreeNode(0);
        TreeNode nodeFour = new TreeNode(1);
        TreeNode nodeFive = new TreeNode(6);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.right = nodeFive;

        unit.averageOfSubtree(root);

        System.out.print("Average of subtree for the given tree is " + unit.result);
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    private int result;

    public int averageOfSubtree(TreeNode root) {
        processToComputeAverageOfSubTree(root);
        return result;
    }

    private IntermittentNode processToComputeAverageOfSubTree(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        IntermittentNode leftInterimNode = null;
        IntermittentNode rightInterimNode = null;

            if (leftNode != null) {
                leftInterimNode = processToComputeAverageOfSubTree(leftNode);
            }

            if (rightNode != null) {
                rightInterimNode = processToComputeAverageOfSubTree(rightNode);
            }

            IntermittentNode resultNode = new IntermittentNode();
            resultNode.sumCollected += node.val;
            resultNode.numberOfNodesCollected += 1;

            if (leftInterimNode != null) {
                resultNode.sumCollected += leftInterimNode.sumCollected;
                resultNode.numberOfNodesCollected += leftInterimNode.numberOfNodesCollected;
            }

            if (rightInterimNode != null) {
            resultNode.sumCollected += rightInterimNode.sumCollected;
            resultNode.numberOfNodesCollected += rightInterimNode.numberOfNodesCollected;
            }

            updateResult(node, resultNode);

            return resultNode;
    }

    private void updateResult(TreeNode node, IntermittentNode resultNode) {
        if (resultNode.sumCollected/resultNode.numberOfNodesCollected == node.val) {
            result++;
        }
    }

    static class IntermittentNode {
        private int sumCollected;
        private int numberOfNodesCollected;
    }
}
