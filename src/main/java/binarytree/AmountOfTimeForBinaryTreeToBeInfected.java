package binarytree;

import java.util.*;

/**
 You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts
 from the node with value start.

 Each minute, a node becomes infected if:

 1.) The node is currently uninfected
 2.) The node is adjacent to an infected node

 Return the number of minutes needed for the entire tree to be infected

 Constraints:

 a.) The number of nodes in the tree is in the range [1, pow(10,5)].
 b.) 1 <= Node.val <= pow(10,5)
 c.) Each node has a unique value.
 d.) A node with a value of start exists in the tree.

 Source : LeetCode

Time Complexity = O(2 * n) ~ O(n), where n being number of nodes in the tree
Space Complexity = O(2 * n) ~ O(n), where n being number of nodes in the tree
 * */

public class AmountOfTimeForBinaryTreeToBeInfected {

    public static void main(String ar[]) {
        AmountOfTimeForBinaryTreeToBeInfected unit = new AmountOfTimeForBinaryTreeToBeInfected();

        TreeNode root = new TreeNode(1);
        TreeNode nodeOne = new TreeNode(5);
        TreeNode nodeTwo = new TreeNode(3);
        TreeNode nodeThree = new TreeNode(4);
        TreeNode nodeFour = new TreeNode(10);
        TreeNode nodeFive = new TreeNode(6);
        TreeNode nodeSix = new TreeNode(9);
        TreeNode nodeSeven = new TreeNode(2);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.right = nodeThree;

        nodeTwo.left = nodeFour;
        nodeTwo.right = nodeFive;

        nodeThree.left = nodeSix;
        nodeThree.right = nodeSeven;

        System.out.println("Amount of time taken for the entire tree to be infected is " + unit.amountOfTime(root, 3));

    }


    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, Pair> map = new HashMap<>();
        traverseTree(root, map);
        map.put(root.val, new Pair(root, null));

        return computeAmountOfTime(map, start);
    }

    private int computeAmountOfTime(Map<Integer, Pair> map, int start) {
        Set<Integer> infectedNodes = new HashSet<>();
        Pair startingPair = map.get(start);

        Queue<Node> queue = new LinkedList<>();
        TreeNode node = startingPair.node;
        int amountOfTimeTaken = 0;

        Node startNode = new Node(node, 0);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node peekedNode = queue.peek();

            if (peekedNode.node.left != null && !infectedNodes.contains(peekedNode.node.left.val)) {
                queue.add(new Node(peekedNode.node.left, peekedNode.time + 1));
            }

            if (peekedNode.node.right != null && !infectedNodes.contains(peekedNode.node.right.val)) {
                queue.add(new Node(peekedNode.node.right, peekedNode.time + 1));
            }

            TreeNode parentNode = map.get(peekedNode.node.val).parentNode;

            if (parentNode != null && !infectedNodes.contains(parentNode.val)) {
                queue.add(new Node(parentNode, peekedNode.time + 1));
            }

            peekedNode = queue.poll();
            amountOfTimeTaken = updateTime(amountOfTimeTaken, peekedNode.time);
            infectedNodes.add(peekedNode.node.val);
        }
        return amountOfTimeTaken;
    }

    private int updateTime(int amountOfTimeTaken, int time) {
        return time >= amountOfTimeTaken ? time : amountOfTimeTaken;
    }

    private void traverseTree(TreeNode node, Map<Integer, Pair> map) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            traverseTree(leftNode, map);
            map.put(leftNode.val, new Pair(leftNode, node));
        }

        if (rightNode != null) {
            traverseTree(rightNode, map);
            map.put(rightNode.val, new Pair(rightNode, node));
        }
    }

    static class Node {
        private int time;
        private TreeNode node;

        public Node(TreeNode node, int time) {
            this.time = time;
            this.node = node;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { this.val = val; }
    }

    static class Pair {
        private TreeNode node;
        private TreeNode parentNode;

        public Pair(TreeNode node, TreeNode parentNode) {
            this.node = node;
            this.parentNode = parentNode;
        }
    }
}
