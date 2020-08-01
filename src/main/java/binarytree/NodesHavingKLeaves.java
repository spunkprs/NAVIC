package binarytree;

public class NodesHavingKLeaves {

    private boolean flag = false;

    public void btWithKleaves(Node root, int k) {
        int sum = processToPrintNodesWithKLeaves(root, k);
        if (sum == k) {
            flag = true;
            System.out.print(root.data);
        }

        if (!flag) {
            System.out.print(-1);
        }
    }

    private int processToPrintNodesWithKLeaves(Node node, int k) {
        Node leftNode = node.left;

        Node rightNode = node.right;

        int sum = 0;

        if (leftNode != null) {
            sum +=processToPrintNodesWithKLeaves(leftNode, k);
        }

        if (rightNode != null) {
            sum +=processToPrintNodesWithKLeaves(rightNode, k);
        }

        if (leftNode == null && rightNode == null) {
            return 1;
        }

        if (sum == k) {
            flag = true;
            System.out.print(node.data);
            System.out.print(" ");
        }

        return sum;
    }
}
