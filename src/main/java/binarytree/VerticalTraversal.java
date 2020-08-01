package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerticalTraversal {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
        int min_element = Integer.MAX_VALUE;
        int max_element = Integer.MIN_VALUE;
        Pair pair = new Pair(min_element, max_element);
        traverseTree(root, map, 0, pair, 0);
        return prepareOutputList(map, pair);
    }

    private List<List<Integer>> prepareOutputList(Map<Integer, ListNode> map, Pair pair) {
        List<List<Integer>> outputList = new ArrayList<List<Integer>>();
        for (int element = pair.minElement; element <= pair.maxElement; element++) {
            List<Integer> list = new ArrayList<Integer>();
            ListNode node = map.get(element);
            if (node.previous == node) {
                list.add(node.treeNode.val);
            } else {
                ListNode tail = node.previous;
                while (node != tail) {
                    list.add(node.treeNode.val);
                    node = node.next;
                }
                list.add(tail.treeNode.val);
            }
            outputList.add(list);
        }
        return outputList;
    }

    private void traverseTree(TreeNode node, Map<Integer, ListNode> map, int num, Pair pair, int depth) {

        updatePair(num, pair);

        if (!map.containsKey(num)) {
        ListNode listNode = new ListNode(depth);
        listNode.treeNode = node;
        listNode.previous = listNode;
        listNode.next = listNode;
        map.put(num, listNode);
        } else {
            ListNode head = map.get(num);
            updatePointers(head, depth, node, map, num);
        }

        TreeNode leftChild = node.left;
        if (leftChild != null) {
            traverseTree(leftChild, map, num - 1, pair, depth + 1);
        }

        TreeNode rightChild = node.right;
        if (rightChild != null) {
            traverseTree(rightChild, map, num + 1, pair, depth + 1);
        }
    }

    private void updatePointers(ListNode node, int depth, TreeNode treeNode, Map<Integer, ListNode> map, int num) {
        ListNode ln = new ListNode(depth);
        ln.treeNode = treeNode;

        if (depth <= node.depth) {
            ln.next = node;
            ListNode temp = node.previous;
            ln.previous = temp;
            temp.next = ln;
            node.previous = ln;
            map.put(num, ln);
        } else if (depth >= node.previous.depth) {
            ListNode tail = node.previous;
            tail.next = ln;
            ln.previous = tail;
            ln.next = node;
            node.previous = ln;
        } else {
            ListNode iterableNode = node;
            while (depth > iterableNode.depth) {
                iterableNode = iterableNode.next;
            }
            ListNode prev = iterableNode.previous;
            ln.next = iterableNode;
            iterableNode.previous = ln;
            prev.next = ln;
            ln.previous = prev;
        }
    }

    private void updatePair(int num, Pair pair) {
        if (num < pair.minElement) {
            pair.minElement = num;
        }

        if (num > pair.maxElement) {
            pair.maxElement = num;
        }
    }

    class Pair {
        int minElement;
        int maxElement;

        public Pair(int minElement, int maxElement) {
            this.minElement = minElement;
            this.maxElement = maxElement;
        }
    }

    class ListNode {
        TreeNode treeNode;
        ListNode next;
        ListNode previous;
        int depth;

        ListNode(int depth) {
            this.depth = depth;
        }
    }


}
