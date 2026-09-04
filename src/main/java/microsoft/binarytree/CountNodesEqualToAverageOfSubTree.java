package microsoft.binarytree;

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
