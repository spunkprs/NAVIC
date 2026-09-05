package microsoft.binarytree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
Problem : 2049
Link : https://leetcode.com/problems/count-nodes-with-the-highest-score/description/?envType=problem-list-v2&envId=binary-tree
Level : Medium

There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1.
You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.

Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed.
The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.

Return the number of nodes that have the highest score.

Constraints:-

a.) n == parents.length
b.) 2 <= n <= 10^5
c.) parents[0] == -1
d.) 0 <= parents[i] <= n - 1 for i != 0
e.) parents represents a valid binary tree.

Time Complexity = O(N * Log(N)) --> Because of the usage of TreeMap, I could have avoided it && then this T.C would drop to O(N) instead
Space Complexity = (N)
 * */

public class CountNodesWithHighestScore {

    TreeNode root = null;

    public static void main(String ar[]) {
        CountNodesWithHighestScore unit = new CountNodesWithHighestScore();
        //int arr[] = {-1, 2, 0, 2, 0};
        int arr[] = {-1,0,3,0,3,1};
        System.out.print("Highest score for the tree would be " + unit.countHighestScoreNodes(arr));
    }

    public int countHighestScoreNodes(int[] parents) {
        prepareBinaryTree(parents);
        updateLeftAndRightSubTreeCount(root);

        TreeMap<Long, Integer> frequencyMap = new TreeMap<>();
        processToCountHighestScoreNodes(root, frequencyMap);
        return frequencyMap.get(frequencyMap.lastKey());
    }

    private void processToCountHighestScoreNodes(TreeNode node, TreeMap<Long, Integer> frequencyMap) {
        if (node == root) {
            long product = (node.leftTreeCount != 0 ? node.leftTreeCount : 1) * (node.rightTreeCount != 0 ? node.rightTreeCount : 1);
            pushElementToFrequencyMap(product, frequencyMap);
        } else {
            long pOne = (node.leftTreeCount != 0 ? node.leftTreeCount : 1) * (node.rightTreeCount != 0 ? node.rightTreeCount : 1);
            long pTwo = (root.leftTreeCount != 0 ? root.leftTreeCount : 0) + 1 + (root.rightTreeCount != 0 ? root.rightTreeCount : 0);
            pTwo -=  (node.leftTreeCount != 0 ? node.leftTreeCount : 0) + (node.rightTreeCount != 0 ? node.rightTreeCount : 0) + 1;
            pushElementToFrequencyMap(pOne * pTwo, frequencyMap);
        }

        if (node.left != null) {
            processToCountHighestScoreNodes(node.left, frequencyMap);
        }

        if (node.right != null) {
            processToCountHighestScoreNodes(node.right, frequencyMap);
        }
    }

    private void pushElementToFrequencyMap(long product, TreeMap<Long, Integer> frequencyMap) {
        if (!frequencyMap.containsKey(product)) {
            frequencyMap.put(product, 1);
        } else {
            frequencyMap.put(product, 1 + frequencyMap.get(product));
        }
    }

    private int updateLeftAndRightSubTreeCount(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        int leftCount = 0;
        int rightCount = 0;

        if (leftNode != null) {
            leftCount = updateLeftAndRightSubTreeCount(leftNode);
        }

        if (rightNode != null) {
            rightCount = updateLeftAndRightSubTreeCount(rightNode);
        }

        node.leftTreeCount = leftCount;
        node.rightTreeCount = rightCount;

        return leftCount + rightCount + 1;
    }

    private void prepareBinaryTree(int[] parents) {
        Map<Integer, TreeNode> intermittentMap = new HashMap<>();
        for (int i = 0; i < parents.length; i++) {
            int parent = parents[i];
            if (!intermittentMap.containsKey(i)) {
                TreeNode treeNode = new TreeNode(i);
                intermittentMap.put(i, treeNode);

                if (parent == -1) {
                    root = treeNode;
                } else {
                    if (!intermittentMap.containsKey(parent) && parent != -1) {
                        TreeNode treeNodeParent = new TreeNode(parent);
                        treeNodeParent.left = treeNode;
                        intermittentMap.put(parent, treeNodeParent);
                    } else if (intermittentMap.containsKey(parent) && parent != -1) {
                        TreeNode treeNodeParent = intermittentMap.get(parent);
                        if (treeNodeParent.left == null) {
                            treeNodeParent.left = treeNode;
                        } else if (treeNodeParent.right == null) {
                            treeNodeParent.right = treeNode;
                        }
                    }
                }
            } else {
                TreeNode treeNode = intermittentMap.get(i);
                if (parent == -1) {
                    root = treeNode;
                } else {
                    if (!intermittentMap.containsKey(parent) && parent != -1) {
                        TreeNode treeNodeParent = new TreeNode(parent);
                        treeNodeParent.left = treeNode;
                        intermittentMap.put(parent, treeNodeParent);
                    } else if (intermittentMap.containsKey(parent) && parent != -1) {
                        TreeNode treeNodeParent = intermittentMap.get(parent);
                        if (treeNodeParent.left == null) {
                            treeNodeParent.left = treeNode;
                        } else if (treeNodeParent.right == null) {
                            treeNodeParent.right = treeNode;
                        }
                    }
                }
            }
        }
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        private int leftTreeCount;
        private int rightTreeCount;
        public TreeNode(int val) { this.val = val; }
    }
}
