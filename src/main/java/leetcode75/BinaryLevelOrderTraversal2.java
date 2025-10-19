package leetcode75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryLevelOrderTraversal2 {

    private Node head;
    private Node tail;

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<Node> queue = new LinkedList();

        if (root != null) {
            queue.add(new Node(root, 0));
            head = queue.peek();
            tail = head;

            while (!queue.isEmpty()) {
                Node peekedNode = queue.peek();

                if (peekedNode.treeNode.right != null) {
                    Node node = new Node(peekedNode.treeNode.right, peekedNode.level + 1);
                    queue.add(node);
                    tail.next = node;
                    node.previous = tail;
                    tail = node;
                }

                if (peekedNode.treeNode.left != null) {
                    Node node = new Node(peekedNode.treeNode.left, peekedNode.level + 1);
                    queue.add(node);
                    tail.next = node;
                    node.previous = tail;
                    tail = node;
                }

                queue.poll();
            }

            return generateResult();
        } else {
            List<List<Integer>> result = new ArrayList();
            return result;
        }


    }

    private List<List<Integer>> generateResult() {
        List<List<Integer>> result = new ArrayList();

        Node node = tail;

        while (node != null) {
            int level = node.level;
            List<Integer> list = new ArrayList();

            while (node != null && node.level == level) {
                list.add(node.treeNode.val);
                node = node.previous;
            }
            result.add(list);
        }

        return result;
    }

    static class Node {
        private TreeNode treeNode;
        private int level;
        private Node next;
        private Node previous;

        public Node(TreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    static class TreeNode {
        int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int val) { this.val = val; }
    }


}
