package microsoft.binarytree;

import java.util.Stack;

/**
Problem : 1008

Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.

It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.

A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.


Constraints:-
a.) 1 <= preorder.length <= 100
b.) 1 <= preorder[i] <= 1000
c.) All the values of preorder are unique.

Time Complexity = O(N), where N being number of nodes in the tree
Space Complexity = O(N), where N being number of nodes in the tree [usage of explicit Stack is there]
 * */

public class ConstructBinarySearchTreeFromPreOrderTraversal {

    public static void main(String ar[]) {
        ConstructBinarySearchTreeFromPreOrderTraversal unit = new ConstructBinarySearchTreeFromPreOrderTraversal();
        int preOrder[] = {10, 5, 4, 6, 12, 11, 16};
        TreeNode root = unit.bstFromPreorder(preOrder);
        System.out.print(root.val);
    }

    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        /*TreeNode root = null;

        for (int i = 0; i < preorder.length; i++) {
            if (i == 0) {
                root = new TreeNode(preorder[i]);
            } else {
                findPositionForInsert(root, preorder[i]);
            }
        }
        return root;*/
        return prepareBSTApproachTwo(preorder);
    }

    private TreeNode prepareBSTApproachTwo(int[] preorder) {
        Stack<Node> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        Node rootNode = new Node(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        stack.push(rootNode);

        for (int i = 1; i < preorder.length; i++) {
            Node peekedNode = stack.peek();
            if (preorder[i] <= peekedNode.maxVal && preorder[i] >= peekedNode.minVal) {
                pushNodeToStack(i, preorder, peekedNode, stack);
            } else {
                while (!(preorder[i] <= stack.peek().maxVal && preorder[i] >= stack.peek().minVal)) {
                    stack.pop();
                }
                peekedNode = stack.peek();
                if (preorder[i] <= peekedNode.maxVal && preorder[i] >= peekedNode.minVal) {
                    pushNodeToStack(i, preorder, peekedNode, stack);
                }
            }
        }
        return root;
    }

    private void pushNodeToStack(int index, int preorder[], Node peekedNode, Stack<Node> stack) {
        if (preorder[index] < peekedNode.treeNode.val) {
            TreeNode createdTreeNode = new TreeNode(preorder[index]);
            peekedNode.treeNode.left = createdTreeNode;
            Node createdNode = new Node(createdTreeNode, peekedNode.minVal, peekedNode.treeNode.val - 1);
            stack.push(createdNode);
        } else if (preorder[index] > peekedNode.treeNode.val) {
            TreeNode createdTreeNode = new TreeNode(preorder[index]);
            peekedNode.treeNode.right = createdTreeNode;
            Node createdNode = new Node(createdTreeNode, peekedNode.treeNode.val + 1, peekedNode.maxVal);
            stack.push(createdNode);
        }
    }

    static class Node {
        private TreeNode treeNode;
        private int minVal;
        private int maxVal;

        public Node(TreeNode treeNode, int minVal, int maxVal) {
            this.treeNode = treeNode;
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }

    /**
    Time Complexity = O(N^2), where N being number of nodes in the tree
     * */
    private void findPositionForInsert(TreeNode root, int num) {
        insertNode(root, num);
    }

    private void insertNode(TreeNode node, int num) {
        if (num < node.val) {
            if (node.left == null) {
                TreeNode newNode = new TreeNode(num);
                node.left = newNode;
            } else {
                insertNode(node.left, num);
            }
        } else if (num > node.val) {
            if (node.right == null) {
                TreeNode newNode = new TreeNode(num);
                node.right = newNode;
            } else {
                insertNode(node.right, num);
            }
        }
    }
}
