package microsoft.binarytree;

import java.util.*;
import java.util.stream.Collectors;

/**
Problem : 2471
Link : https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/description/

You are given the root of a binary tree with unique values.

In one operation, you can choose any two nodes at the same level and swap their values.

Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.

The level of a node is the number of edges along the path between it and the root node.


Constraints:-

a.) The number of nodes in the tree is in the range [1, 10^5].
b.) 1 <= Node.val <= 10^5
c.) All the values of the tree are unique.
 * */

public class MinimumNumberOfOperationsToSortBinaryTreeByLevel {

    public static void main(String ar[]) {
        TreeNode root = new TreeNode(1);
        TreeNode nodeOne = new TreeNode(4);
        TreeNode nodeTwo = new TreeNode(3);
        TreeNode nodeThree = new TreeNode(7);
        TreeNode nodeFour = new TreeNode(6);
        TreeNode nodeFive = new TreeNode(8);
        TreeNode nodeSix = new TreeNode(5);
        TreeNode nodeSeven = new TreeNode(9);
        TreeNode nodeEight = new TreeNode(10);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.left = nodeFive;
        nodeTwo.right = nodeSix;

        nodeFive.left = nodeSeven;
        nodeSix.left = nodeEight;

        MinimumNumberOfOperationsToSortBinaryTreeByLevel unit = new MinimumNumberOfOperationsToSortBinaryTreeByLevel();

        System.out.print("Minimum number of operations to sort binary tree at level is " + unit.minimumOperations(root));

    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    static class Node {
        private int level;
        private TreeNode node;

        public Node(int level, TreeNode node) {
            this.level = level;
            this.node = node;
        }
    }

    public int minimumOperations(TreeNode root) {
        Map<Integer, List<Integer>> intermittentMap = new HashMap<>();
        Queue<Node> intermittentQueue = new LinkedList<>();
        Node node = new Node(0, root);
        intermittentQueue.add(node);

        while (!intermittentQueue.isEmpty()) {
            Node polledNode = intermittentQueue.poll();
            pushElementToMap(polledNode, intermittentMap);

            if (polledNode.node.left != null) {
                intermittentQueue.add(new Node(polledNode.level + 1, polledNode.node.left));
            }

            if (polledNode.node.right != null) {
                intermittentQueue.add(new Node(polledNode.level + 1, polledNode.node.right));
            }
        }

        return processToComputeMinimumOperations(intermittentMap);
        //return 0;
    }

    private int processToComputeMinimumOperations(Map<Integer, List<Integer>> intermittentMap) {
        int minOperations = 0;
        for (Integer key : intermittentMap.keySet()) {
            List<Integer> existingElements = intermittentMap.get(key);
            if (existingElements.size() > 1) {
                List<Integer> copiedElements = existingElements.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                Map<Integer, Integer> helperMap = new HashMap<>();
                for (int i = 0; i < copiedElements.size(); i++) {
                    helperMap.put(copiedElements.get(i), i);
                }

                for (int i = 0; i < existingElements.size(); i++) {
                    int indexToBePresent = helperMap.get(existingElements.get(i));
                    while (indexToBePresent != i) {
                        int elementOne = existingElements.get(indexToBePresent);
                        int elementTwo = existingElements.get(i);
                        minOperations++;
                        existingElements.set(indexToBePresent, elementTwo);
                        existingElements.set(i, elementOne);
                        indexToBePresent = helperMap.get(existingElements.get(i));
                    }
                }
            }
        }
        return minOperations;
    }

    private void pushElementToMap(Node polledNode, Map<Integer, List<Integer>> intermittentMap) {
        if (!intermittentMap.containsKey(polledNode.level)) {
            List<Integer> elements = new ArrayList<>();
            elements.add(polledNode.node.val);
            intermittentMap.put(polledNode.level, elements);
        } else {
            intermittentMap.get(polledNode.level).add(polledNode.node.val);
        }
    }


}
