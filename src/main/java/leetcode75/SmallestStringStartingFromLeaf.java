package leetcode75;

import binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 You are given the root of a binary tree where each node has a value in the range
 [0, 25] representing the letters 'a' to 'z'.

 Return the lexicographically smallest string that starts at a leaf of this tree
 and ends at the root.

 As a reminder, any shorter prefix of a string is lexicographically smaller.

 For example, "ab" is lexicographically smaller than "aba".
 A leaf of a node is a node that has no children.

Time Complexity = O(n) --> Iterating every node in the tree
Space Complexity = O(n) --> Method stack will take space in proportion to the depth of the tree which
could be n in the worst case

Source : LeetCode
Level : Medium
 * */


public class SmallestStringStartingFromLeaf {

    private String smallestString;
    private ListNode head;
    private ListNode tail;

    public String smallestFromLeaf(TreeNode root) {
        Map<Integer, Character> map = new HashMap();

        map.put(0, 'a');
        map.put(1, 'b');
        map.put(2, 'c');
        map.put(3, 'd');
        map.put(4, 'e');
        map.put(5, 'f');
        map.put(6, 'g');
        map.put(7, 'h');

        map.put(8, 'i');
        map.put(9, 'j');
        map.put(10, 'k');
        map.put(11, 'l');
        map.put(12, 'm');
        map.put(13, 'n');
        map.put(14, 'o');
        map.put(15, 'p');

        map.put(16, 'q');
        map.put(17, 'r');
        map.put(18, 's');
        map.put(19, 't');
        map.put(20, 'u');
        map.put(21, 'v');
        map.put(22, 'w');
        map.put(23, 'x');

        map.put(24, 'y');
        map.put(25, 'z');

        processToComputeSmallestString(root, map);
        return smallestString;
    }

    private void processToComputeSmallestString(TreeNode node, Map<Integer, Character> map) {
        head = new ListNode(node.getVal());
        tail = new ListNode(node.getVal());

        process(node, map);
    }

    private void process(TreeNode node, Map<Integer, Character> map) {
        if (node.getLeft() == null && node.getRight() == null) {
            if (smallestString != null) {
                String appendedString = prepareString(map);
                smallestString = smallestString.compareTo(appendedString) < 0 ? smallestString : smallestString.compareTo(appendedString) > 0 ? appendedString : smallestString;
            } else {
                smallestString = prepareString(map);
            }
        }

        if (node.getLeft() != null) {
            ListNode listNode = new ListNode(node.getLeft().getVal());
            tail.next = listNode;
            listNode.previous = tail;
            tail = listNode;
            process(node.getLeft(), map);
            tail = tail.previous;
        }

        if (node.getRight() != null) {
            ListNode listNode = new ListNode(node.getRight().getVal());
            tail.next = listNode;
            listNode.previous = tail;
            tail = listNode;
            process(node.getRight(), map);
            tail = tail.previous;
        }
    }

    private String prepareString(Map<Integer, Character> map) {
        ListNode node = tail;
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(map.get(node.index));
            node = node.previous;
        }
        return sb.toString();
    }

    class ListNode {
        private int index;

        public ListNode(int index) {
            this.index = index;
        }

        private ListNode next;
        private ListNode previous;
    }
}
