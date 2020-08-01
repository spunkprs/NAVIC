package binarytree;

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeFromLinkedList {

    public static class Node {
        int data;
        public Node next;
        public Node(int data) {
            this.data = data;
        }
    }


    public static class Tree{
        int data;
        Tree left;
        Tree right;
        public Tree(int data){
            this.data = data;
            left=null;
            right=null;
        }
    }

    public static Tree convert(Node head, Tree node) {
        Map<Node, Tree> map = new HashMap<Node, Tree>();
        return processToConvert(head, node, map);
    }

    private static Tree processToConvert(Node head, Tree node, Map<Node, Tree> map) {
        Node start = head;
        Node leftChild = null;
        Node rightChild = null;

        node = new Tree(start.data);
        Tree parent = node;
        map.put(start, node);

        if (head.next != null) {
            leftChild = head.next;
            Tree leftTreeNode = new Tree(leftChild.data);
            parent.left = leftTreeNode;
            map.put(leftChild, leftTreeNode);
        }

        if (leftChild != null && leftChild.next != null) {
            rightChild = leftChild.next;
            Tree rightTreeNode = new Tree(rightChild.data);
            parent.right = rightTreeNode;
            map.put(rightChild, rightTreeNode);
        }



        while (leftChild != null && rightChild != null) {
            leftChild = rightChild.next;
            start = start.next;
            if (leftChild != null) {
                Tree leftTreeNode = new Tree(leftChild.data);
                map.get(start).left = leftTreeNode;
                map.put(leftChild, leftTreeNode);
                if (leftChild.next != null) {
                    rightChild = leftChild.next;
                    Tree rightTreeNode = new Tree(rightChild.data);
                    map.get(start).right = rightTreeNode;
                    map.put(rightChild, rightTreeNode);
                } else {
                    rightChild = null;
                }
            }
        }
        return node;
    }
}
