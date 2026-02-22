package microsoft.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
Problem : 513
Given the root of a binary tree, return the leftmost value in the last row of the tree

Constraints:-

a.) The number of nodes in the tree is in the range [1, 104].
b.) -231 <= Node.val <= 231 - 1

Time Complexity : O(N), where N being number of nodes in the tree
Space Complexity : O(W), where W is the number of nodes in the level having maximum nodes
 * */

public class BottomLeftTreeValue {

    public static void main(String ar[]) {
        //No code here to validate as it's been accepted in the first submission !!
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        } else {
            return processToFindBottomLeftValue(root);
        }
    }

    private int processToFindBottomLeftValue(TreeNode treeNode) {
        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(0, treeNode);
        queue.add(node);
        int num = treeNode.val;
        int level = node.depth;

        while (!queue.isEmpty()) {
            Node peekedNode = queue.peek();
            TreeNode leftNode = peekedNode.treeNode.left;
            TreeNode rightNode = peekedNode.treeNode.right;

            if (leftNode != null) {
                Node nLeft = new Node(peekedNode.depth + 1,  leftNode);
                if (nLeft.depth > level) {
                    level = nLeft.depth;
                    num = nLeft.treeNode.val;
                }
                queue.add(nLeft);
            }

            if (rightNode != null) {
                Node nRight = new Node(peekedNode.depth + 1,  rightNode);
                if (nRight.depth > level) {
                    level = nRight.depth;
                    num = nRight.treeNode.val;
                }
                queue.add(nRight);
            }
            queue.poll();
        }

       return num;
    }

    static class Node {
        private int depth;
        private TreeNode treeNode;

        public Node(int depth, TreeNode treeNode) {
            this.depth = depth;
            this.treeNode = treeNode;
        }
    }


    public class TreeNode {
       private int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
    }
}
