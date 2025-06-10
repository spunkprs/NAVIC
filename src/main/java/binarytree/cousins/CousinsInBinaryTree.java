package binarytree.cousins;

/**
 Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true
 if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.

 Two nodes of a binary tree are cousins if they have the same depth with different parents.

 Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1

 Credits --> LeetCode

 Time Complexity = O(n)
 Space Complexity = O(n)
 Where n being number of nodes in the binary tree
 * */

public class CousinsInBinaryTree {

    private boolean isNodeFound = false;
    private boolean parentFound = false;

    public static void main(String ar[]) {
        CousinsInBinaryTree unit = new CousinsInBinaryTree();

        TreeNode leafOne = new TreeNode(7);
        TreeNode leafTwo = new TreeNode(5);

        TreeNode leafThree = new TreeNode(9);
        TreeNode leafFour = new TreeNode(8);

        TreeNode internalNodeOne = new TreeNode(6);
        internalNodeOne.right = leafOne;

        TreeNode internalNodeTwo = new TreeNode(3);
        internalNodeTwo.right = leafTwo;

        TreeNode internalNodeThree = new TreeNode(2);

        internalNodeThree.left = internalNodeOne;
        internalNodeThree.right = internalNodeTwo;

        TreeNode internalNodeFour = new TreeNode(4);

        internalNodeFour.left = leafThree;
        internalNodeFour.right = leafFour;

        TreeNode root = new TreeNode(1);

        root.left = internalNodeThree;
        root.right = internalNodeFour;

        System.out.println(unit.isCousins(root, 7 , 5));
    }



    public boolean isCousins(TreeNode root, int x, int y) {

        int depthOfFirstNode = findDepthOfNode(root, x);
        isNodeFound = false;
        int depthOfSecondNode = findDepthOfNode(root, y);
        isNodeFound = false;

        if (depthOfFirstNode == depthOfSecondNode) {
            TreeNode parentOne = findParentOfNode(root, x);
            parentFound = false;
            TreeNode parentTwo = findParentOfNode(root, y);

            if (parentOne != parentTwo) {
                return true;
            }
        }
        return false;
    }

    private TreeNode findParentOfNode(TreeNode root, int x) {
        return processToFindParentOfNode(root, x);
    }

    private TreeNode processToFindParentOfNode(TreeNode node, int x) {
        TreeNode left = node.left;
        TreeNode right = node.right;

        TreeNode result = null;

        if (left != null) {
            if (left.val == x) {
                result = node;
                parentFound = true;
            } else {
                result = processToFindParentOfNode(left, x);
            }
        }

        if (parentFound) {
            return result;
        }

        if (right != null) {
            if (right.val == x) {
                result = node;
                parentFound = true;
            } else {
                result = processToFindParentOfNode(right, x);
            }
        }
        return result;
    }

    private int findDepthOfNode(TreeNode node, int x) {
        int depth = 0;
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (node.val == x) {
            depth = 1;
            isNodeFound = true;
            return depth;
        } else {
            if (leftNode != null) {
                depth = findDepthOfNode(leftNode, x);
                int increment = isNodeFound ? 1: 0;
                depth += increment;
            }

            if (isNodeFound) {
                return depth;
            }

            if (rightNode != null) {
                depth = findDepthOfNode(rightNode, x);
                int increment = isNodeFound ? 1: 0;
                depth += increment;
            }
        }
        return depth;
    }

    static class TreeNode {
       private  int val;
       private TreeNode left;
       private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
