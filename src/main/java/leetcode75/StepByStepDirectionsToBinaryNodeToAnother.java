package leetcode75;


/**
Problem : 2096
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n.
You are also given an integer startValue representing the value of the start node s, and a different integer
destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path
 as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t

Constraints:-

a.) The number of nodes in the tree is n.
b.) 2 <= n <= 10^5
c.) 1 <= Node.val <= n
d.) All the values in the tree are unique.
e.) 1 <= startValue, destValue <= n
f.) startValue != destValue


Time Complexity : O(N), where N being number of nodes in the tree
Space Complexity : O(H), where H being depth of the tree

 * */

public class StepByStepDirectionsToBinaryNodeToAnother {

    private Node headOne;
    private Node tailOne;
    private Node headTwo;
    private Node tailTwo;
    private boolean shallContinue = true;

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    static class Node {
        private int num;
        private Node nextNode;
        private Node prevNode;
        private boolean isLeftChild;
        private boolean isRoot;

        public Node(int num) {
            this.num = num;
        }
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        processToGeneratePathOne(root, startValue);
        shallContinue = true;
        processToGeneratePathTwo(root, destValue);
        return computeDirectionsPostFindingLCA();
    }

    private void processToGeneratePathOne(TreeNode root, int num) {
        processOne(root, num, null);
    }

    private void processToGeneratePathTwo(TreeNode root, int num) {
        processTwo(root, num, null);
    }

    private void processOne(TreeNode node, int num, String childType) {
        if (headOne == null) {
            headOne = new Node(node.val);
            headOne.isRoot = true;
            tailOne = headOne;
        } else {
            Node listNode = new Node(node.val);
            if (childType.equals("L")) {
                listNode.isLeftChild = true;
            } else if (childType.equals("R")) {
                listNode.isLeftChild = false;
            }
            tailOne.nextNode = listNode;
            listNode.prevNode = tailOne;
            tailOne = tailOne.nextNode;
        }

        if (node.val != num) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null) {
                processOne(leftNode, num, "L");
                if (shallContinue) {
                    tailOne = tailOne.prevNode;
                    tailOne.nextNode = null;
                }
            }

            if (rightNode != null && shallContinue) {
                processOne(rightNode, num, "R");
                if (shallContinue) {
                    tailOne = tailOne.prevNode;
                    tailOne.nextNode = null;
                }
            }
        } else {
            shallContinue = false;
        }
    }

    private void processTwo(TreeNode node, int num, String childType) {
        if (headTwo == null) {
            headTwo = new Node(node.val);
            headTwo.isRoot = true;
            tailTwo = headTwo;
        } else {
            Node listNode = new Node(node.val);
            if (childType.equals("L")) {
                listNode.isLeftChild = true;
            } else if (childType.equals("R")) {
                listNode.isLeftChild = false;
            }
            tailTwo.nextNode = listNode;
            listNode.prevNode = tailTwo;
            tailTwo = tailTwo.nextNode;
        }

        if (node.val != num) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null) {
                processTwo(leftNode, num, "L");
                if (shallContinue) {
                    tailTwo = tailTwo.prevNode;
                    tailTwo.nextNode = null;
                }
            }

            if (rightNode != null && shallContinue) {
                processTwo(rightNode, num, "R");
                if (shallContinue) {
                    tailTwo = tailTwo.prevNode;
                    tailTwo.nextNode = null;
                }
            }
        } else {
            shallContinue = false;
        }
    }

    private String computeDirectionsPostFindingLCA() {
        Node tempNodeOne = headOne;
        Node tempNodeTwo = headTwo;

        StringBuilder sb = new StringBuilder();

        while (tempNodeOne != null && tempNodeTwo != null && tempNodeOne.num == tempNodeTwo.num) {
            tempNodeOne = tempNodeOne.nextNode;
            tempNodeTwo = tempNodeTwo.nextNode;
        }

        Node lcaOne = null;
        Node lcaTwo = null;

        if (tempNodeOne != null) {
            lcaOne = tempNodeOne.prevNode;
        }

        if (tempNodeTwo != null) {
            lcaTwo = tempNodeTwo.prevNode;
        }

        if (lcaOne != null && lcaTwo != null) {
            while(tailOne.num != lcaOne.num) {
                sb.append("U");
                tailOne = tailOne.prevNode;
            }
            while (tempNodeTwo != null) {
                String direction = tempNodeTwo.isLeftChild ? "L" : "R";
                sb.append(direction);
                tempNodeTwo = tempNodeTwo.nextNode;
            }
        } else if (lcaOne == null && lcaTwo != null) {
            while (tempNodeTwo != null) {
                String direction = tempNodeTwo.isLeftChild ? "L" : "R";
                sb.append(direction);
                tempNodeTwo = tempNodeTwo.nextNode;
            }
        } else if (lcaOne != null && lcaTwo == null) {
            while(tailOne.num != lcaOne.num) {
                sb.append("U");
                tailOne = tailOne.prevNode;
            }
        }

        return sb.toString();
    }
}
