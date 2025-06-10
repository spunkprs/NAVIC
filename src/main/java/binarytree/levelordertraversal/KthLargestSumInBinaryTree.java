package binarytree.levelordertraversal;

import java.util.*;

/**
 You are given the root of a binary tree and a positive integer k.

 The level sum in the tree is the sum of the values of the nodes that are on the same level.

 Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.

 Note that two nodes are on the same level if they have the same distance from the root.

 Credits --> LeetCode

 Time Complexity = O(n)
 Space Complexity = O(n)
 Where n being number of nodes in the binary tree


 Have made used of following data structures to solve the problem :-
*/

public class KthLargestSumInBinaryTree {

    public static void main(String ar[]) {
        KthLargestSumInBinaryTree unit = new KthLargestSumInBinaryTree();

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

        int k = 2;
        System.out.println("Kth largest sum against above tree where k is " + k + " is "+ unit.kthLargestLevelSum(root, k));
    }


    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Long> levelSumMap = new HashMap<>();
        queue.add(new Node(0, root));

        levelSumMap.put(0, new Long(root.val));

        while (!queue.isEmpty()) {

            Node queueNode = queue.peek();
            TreeNode node = queueNode.treeNode;
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null) {
                queue.add(new Node(queueNode.level + 1, leftNode));
                updateLevelSumMap(queueNode.level + 1, leftNode, levelSumMap);
            }

            if (rightNode != null) {
                queue.add(new Node(queueNode.level + 1, rightNode));
                updateLevelSumMap(queueNode.level + 1, rightNode, levelSumMap);
            }

            queue.poll();
        }

        if (levelSumMap.size() >= k) {
            return processToFindKthLargestLevelSum(levelSumMap, k);
        }

        return -1;
    }

    private long processToFindKthLargestLevelSum(Map<Integer, Long> levelSumMap, int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        for (Integer key : levelSumMap.keySet()) {
            if (minHeap.size() < k) {
                minHeap.add(levelSumMap.get(key));
            } else if (minHeap.size() == k) {
                if (levelSumMap.get(key) > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.add(levelSumMap.get(key));
                }
            }
        }
        return minHeap.peek();
    }

    private void updateLevelSumMap(int level, TreeNode node, Map<Integer, Long> levelSumMap) {
        if (!levelSumMap.containsKey(level)) {
            levelSumMap.put(level, new Long(node.val));
        } else {
            levelSumMap.put(level, levelSumMap.get(level) + node.val);
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
        private int level;
        private TreeNode treeNode;

        public Node(int level, TreeNode treeNode) {
            this.level = level;
            this.treeNode = treeNode;
        }
    }

}
