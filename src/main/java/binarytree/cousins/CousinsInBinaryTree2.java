package binarytree.cousins;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.

 Two nodes of a binary tree are cousins if they have the same depth with different parents.

 Return the root of the modified tree.

 Note that the depth of a node is the number of edges in the path from the root node to it.

 Credits --> LeetCode
 * */

public class CousinsInBinaryTree2 {

    public static void main(String ar[]) {
        CousinsInBinaryTree2 unit = new CousinsInBinaryTree2();

        TreeNode root = new TreeNode(5);

        System.out.println("Tree post modification according to cousins rules is " + unit.replaceValueInTree(root));
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        Map<Integer, Long> levelSumMap = new HashMap<>();

        processToUpdateLevelSumMap(root, levelSumMap, 0);

        updateStructureOfTree(root, levelSumMap);
        return root;
    }

    private void updateStructureOfTree(TreeNode root, Map<Integer, Long> levelSumMap) {
        Queue<Node> queue = new LinkedList<>();

    }

    private void processToUpdateLevelSumMap(TreeNode node, Map<Integer, Long> levelSumMap, int level) {
        if (!levelSumMap.containsKey(level)) {
            levelSumMap.put(level, new Long(node.val));
        } else {
            levelSumMap.put(level, levelSumMap.get(level) + node.val);
        }

        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            processToUpdateLevelSumMap(leftNode, levelSumMap, level + 1);
        }

        if (rightNode != null) {
            processToUpdateLevelSumMap(rightNode, levelSumMap, level + 1);
        }
    }

    static class TreeNode {
        private  int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    static class Node {
        private int initialValue;
        private boolean isLeftChild;
        private int level;
        private TreeNode treeNode;

        public Node(int initialValue, boolean isLeftChild, int level, TreeNode treeNode) {
            this.initialValue = initialValue;
            this.isLeftChild = isLeftChild;
            this.level = level;
            this.treeNode = treeNode;
        }
    }
}
