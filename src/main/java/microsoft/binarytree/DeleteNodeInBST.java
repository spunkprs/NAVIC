package microsoft.binarytree;

/**
Problem : 450
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

a.) Search for a node to remove.
b.) If the node is found, delete the node.

Constraints:-

a.) The number of nodes in the tree is in the range [0, 10^4].
b.) -10^5 <= Node.val <= 10^5
c.) Each node has a unique value.
d.) root is a valid binary search tree.
e.) -10^5 <= key <= 10^5
 * */

public class DeleteNodeInBST {

    public static void main(String ar[]) {
        DeleteNodeInBST unit = new DeleteNodeInBST();

        TreeNode root = new TreeNode(5);
        TreeNode nodeOne = new TreeNode(3);
        TreeNode nodeTwo = new TreeNode(6);
        TreeNode nodeThree = new TreeNode(2);
        TreeNode nodeFour = new TreeNode(4);
        TreeNode nodeFive = new TreeNode(7);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.right = nodeFive;

        int key = 0;

        System.out.print("Tree post deletion of key " + key + unit.deleteNode(root, key));
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode result = root;
        if (root == null) {
            return null;
        } else {
            if (root.val == key) {
                if (root.right != null && root.left != null) {
                    TreeNode smallestNodeInRightSubTree = findSmallestNodeInRightSUbTree(root.right);
                    smallestNodeInRightSubTree.left = root.left;
                    result = root.right;
                } else if (root.right == null && root.left != null) {
                    result = root.left;
                } else if (root.right != null && root.left == null) {
                    result = root.right;
                } else {
                    return null;
                }
            } else {
                findNodeAndMakeChanges(root, null, key);
            }
        }
        return result;
    }

    private void findNodeAndMakeChanges(TreeNode node, TreeNode nodeParent, int key) {
        if (node != null) {
            if (key < node.val) {
                findNodeAndMakeChanges(node.left, node, key);
            } else if (key > node.val) {
                findNodeAndMakeChanges(node.right, node, key);
            } else {
                boolean isLeftSubTreeOperation = node.val < nodeParent.val ? true : false;

                TreeNode smallestNodeInRightSubTree = null;
                if (node.right != null) {
                    smallestNodeInRightSubTree = findSmallestNodeInRightSUbTree(node.right);
                    smallestNodeInRightSubTree.left = node.left;
                }

                if (isLeftSubTreeOperation) {
                    if (node.right != null) {
                        nodeParent.left = node.right;
                    } else {
                        nodeParent.left = node.left;
                    }
                } else {
                    if (node.right != null) {
                        nodeParent.right = node.right;
                    } else {
                        nodeParent.right = node.left;
                    }
                }
            }
        }
    }

    private TreeNode findSmallestNodeInRightSUbTree(TreeNode node) {
        while (node.left != null) {
            node = node.left;
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
