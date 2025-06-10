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

 Time Complexity --> O(n)
 Space Complexity --> Maximum Number of nodes present across a level {Because we are making use of queue based approach to perform level order traversal}
 * */

public class CousinsInBinaryTree2 {

    public static void main(String ar[]) {
        CousinsInBinaryTree2 unit = new CousinsInBinaryTree2();

        TreeNode leafOne = new TreeNode(1);
        TreeNode leafTwo = new TreeNode(10);
        TreeNode leafThree = new TreeNode(7);

        TreeNode internalNodeOne = new TreeNode(4);
        internalNodeOne.left = leafOne;
        internalNodeOne.right = leafTwo;

        TreeNode internalNodeTwo = new TreeNode(9);
        internalNodeTwo.right = leafThree;

        TreeNode root = new TreeNode(5);
        root.left = internalNodeOne;
        root.right = internalNodeTwo;

        TreeNode updatedRoot = unit.replaceValueInTree(root);

        System.out.println("Tree post modification according to cousins rules is " + updatedRoot);
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        Map<Integer, Integer> levelSumMap = new HashMap<>();

        processToUpdateLevelSumMap(root, levelSumMap, 0);

        updateStructureOfTree(root, levelSumMap);
        root.val = 0;
        return root;
    }

    private void updateStructureOfTree(TreeNode node, Map<Integer, Integer> levelSumMap) {
        Queue<Node> queue = new LinkedList<>();
        Node queueNode = new Node(node.val, 0, node);
        queue.add(queueNode);

        while (!queue.isEmpty()) {

            Node qNode = queue.peek();
            TreeNode treeNode = qNode.treeNode;

            TreeNode leftNode = treeNode.left;
            TreeNode rightNode = treeNode.right;

            Node queueNodeLeft = null;
            Node queueNodeRight = null;

            if (leftNode != null) {
                queueNodeLeft = new Node(leftNode.val,  qNode.level + 1, leftNode);
                if (qNode.level + 1 > 1) {
                    int updatedValue = levelSumMap.get(qNode.level + 1) - queueNodeLeft.initialValue;
                    updatedValue -= rightNode != null ? rightNode.val : 0;
                    leftNode.val = updatedValue;
                } else {
                    leftNode.val = 0;
                }
                queue.add(queueNodeLeft);
            }

            if (rightNode != null) {
                queueNodeRight = new Node(rightNode.val,  qNode.level + 1, rightNode);
                if (qNode.level + 1 > 1) {
                    int updatedValue = levelSumMap.get(qNode.level + 1) - queueNodeRight.initialValue;
                    updatedValue -= queueNodeLeft != null ? queueNodeLeft.initialValue : 0;
                    rightNode.val = updatedValue;
                } else {
                    rightNode.val = 0;
                }
                queue.add(queueNodeRight);
            }
            queue.poll();
        }
    }

    private void processToUpdateLevelSumMap(TreeNode node, Map<Integer, Integer> levelSumMap, int level) {
        if (!levelSumMap.containsKey(level)) {
            levelSumMap.put(level, node.val);
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
        private int level;
        private TreeNode treeNode;

        public Node(int initialValue, int level, TreeNode treeNode) {
            this.initialValue = initialValue;
            this.level = level;
            this.treeNode = treeNode;
        }
    }
}
