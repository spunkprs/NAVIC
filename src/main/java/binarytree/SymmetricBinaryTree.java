package binarytree;

public class SymmetricBinaryTree {

    private int flag = 1;

    public int isSymmetric(TreeNode A) {
        TreeNode leftNode = A.left;
        TreeNode rightNode = A.right;
        if (leftNode != null && rightNode != null && leftNode.val == rightNode.val) {
            processToCheckIfTreeIsSymmetric(leftNode, rightNode);
        } else if (leftNode == null && rightNode == null) {
            flag = 1;
        } else {
            flag = 0;
        }
        return flag;
    }

    private void processToCheckIfTreeIsSymmetric(TreeNode nodeOne, TreeNode nodeTwo) {
        TreeNode leftNodeOne = nodeOne.left;
        TreeNode rightNodeTwo = nodeTwo.right;

        TreeNode rightNodeOne = nodeOne.right;
        TreeNode leftNodeTwo = nodeTwo.left;

        if (leftNodeOne != null && rightNodeTwo != null && leftNodeOne.val != rightNodeTwo.val) {
            flag = 0;
        } else if ((leftNodeOne == null && rightNodeTwo != null) || (leftNodeOne != null && rightNodeTwo == null)) {
            flag = 0;
        }

        if (flag == 1) {
            if ((leftNodeOne != null && rightNodeTwo != null) || (leftNodeOne != null && rightNodeTwo == null) || (leftNodeOne == null && rightNodeTwo != null)) {
                processToCheckIfTreeIsSymmetric(leftNodeOne, rightNodeTwo);
            }

        }

        if (flag == 1) {

            if ((rightNodeOne != null && leftNodeTwo != null) || (rightNodeOne != null && leftNodeTwo == null) || (rightNodeOne == null && leftNodeTwo != null)) {
                processToCheckIfTreeIsSymmetric(rightNodeOne, leftNodeTwo);
            }
        }
    }
}
