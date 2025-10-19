package leetcode75;

public class FlattenBinaryTreeToLinkedList {

    public static void main(String ar[]) {
        FlattenBinaryTreeToLinkedList unit = new FlattenBinaryTreeToLinkedList();

        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(5);
        TreeNode four = new TreeNode(3);
        TreeNode five = new TreeNode(4);
        TreeNode six = new TreeNode(6);

        one.left = two;
        one.right = three;

        two.left = four;
        two.right = five;

        three.right = six;

        unit.flatten(one);
    }

    public void flatten(TreeNode root) {
        if (root != null) {
            processToFlatten(root);
        }
        System.out.print("A");
    }

    private TreeNode processToFlatten(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        TreeNode leftResult = null;
        TreeNode rightResult = null;

        if (leftNode == null && rightNode == null) {
            return node;
        } else {
            if (rightNode != null) {
                rightResult = processToFlatten(rightNode);
            }

            if (leftNode != null) {
                leftResult = processToFlatten(leftNode);
            }
        }

        if (leftResult != null && rightResult != null) {
            TreeNode lastNode = fetchLastNode(leftResult);
            lastNode.right = rightResult;
            node.right = leftResult;
            node.left = null;
        } else if (leftResult != null && rightResult == null) {
            node.right = leftResult;
            node.left = null;
        } else if (leftResult == null && rightResult != null) {
            node.right = rightResult;
        }

        return node;
    }

    private TreeNode fetchLastNode(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int val) { this.val = val; }
    }
}
