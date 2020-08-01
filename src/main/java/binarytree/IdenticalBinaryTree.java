package binarytree;

public class IdenticalBinaryTree {

    private int flag = 1;

    public int isSameTree(TreeNode A, TreeNode B) {
        processToFindIfTreesAreIdentical(A, B);
        return flag;
    }

    private void processToFindIfTreesAreIdentical(TreeNode nodeOne, TreeNode nodeTwo) {
        if (nodeOne != null && nodeTwo != null && nodeOne.val == nodeTwo.val) {
            TreeNode leftOne = nodeOne.left;
            TreeNode rightOne = nodeOne.right;

            TreeNode leftTwo = nodeTwo.left;
            TreeNode rightTwo = nodeTwo.right;

            if (flag == 1) {
                processToFindIfTreesAreIdentical(leftOne, leftTwo);
            }

            if (flag == 1) {
                processToFindIfTreesAreIdentical(rightOne, rightTwo);
            }

        } else if ((nodeOne != null && nodeTwo == null) || (nodeOne == null && nodeTwo != null)) {
            flag = 0;
        } else if (nodeOne != null && nodeTwo != null && nodeOne.val != nodeTwo.val) {
            flag = 0;
        }
    }
}
