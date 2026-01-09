package leetcode75;

public class FindDistanceInBinaryTree {

    private Node headOne;
    private Node tailOne;
    private Node headTwo;
    private Node tailTwo;
    private boolean shallContinue = true;

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int findDistance(TreeNode root, int p, int q) {
        processToGeneratePathOne(root, p);
        shallContinue = true;
        processToGeneratePathTwo(root, q);
        return findDistancePostFindingLCA();
    }

    private int findDistancePostFindingLCA() {
        Node tempNodeOne = headOne;
        Node tempNodeTwo = headTwo;

        while (tempNodeOne.nextNode != null && tempNodeTwo.nextNode != null && tempNodeOne.num == tempNodeTwo.num) {
            tempNodeOne = tempNodeOne.nextNode;
            tempNodeTwo = tempNodeTwo.nextNode;
        }

        int distanceOne = 1;
        int distanceTwo = 1;

        while (tempNodeOne.num != tailOne.num) {
            distanceOne++;
            tempNodeOne = tempNodeOne.nextNode;
        }

        distanceOne--;

        while (tempNodeTwo.num != tailTwo.num) {
            distanceTwo++;
            tempNodeTwo = tempNodeTwo.nextNode;
        }

        distanceTwo--;

        return distanceOne + distanceTwo;
    }

    private void processToGeneratePathOne(TreeNode root, int num) {
        processOne(root, num);
    }

    private void processToGeneratePathTwo(TreeNode root, int num) {
        processTwo(root, num);
    }

    private void processOne(TreeNode node, int num) {
        if (headOne == null) {
            headOne = new Node(node.val);
            tailOne = headOne;
        } else {
            Node listNode = new Node(node.val);
            tailOne.nextNode = listNode;
            listNode.prevNode = tailOne;
            tailOne = tailOne.nextNode;
        }

        if (node.val != num) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null) {
                processOne(leftNode, num);
                if (shallContinue) {
                    tailOne = tailOne.prevNode;
                    tailOne.nextNode = null;
                }
            }

            if (rightNode != null && shallContinue) {
                processOne(rightNode, num);
                if (shallContinue) {
                    tailOne = tailOne.prevNode;
                    tailOne.nextNode = null;
                }
            }
        } else {
            shallContinue = false;
        }
    }

    private void processTwo(TreeNode node, int num) {
        if (headTwo == null) {
            headTwo = new Node(node.val);
            tailTwo = headTwo;
        } else {
            Node listNode = new Node(node.val);
            tailTwo.nextNode = listNode;
            listNode.prevNode = tailTwo;
            tailTwo = tailTwo.nextNode;
        }

        if (node.val != num) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null) {
                processTwo(leftNode, num);
                if (shallContinue) {
                    tailTwo = tailTwo.prevNode;
                    tailTwo.nextNode = null;
                }
            }

            if (rightNode != null && shallContinue) {
                processTwo(rightNode, num);
                if (shallContinue) {
                    tailTwo = tailTwo.prevNode;
                    tailTwo.nextNode = null;
                }
            }
        } else {
            shallContinue = false;
        }
    }

    static class Node {
        private int num;
        private Node nextNode;
        private Node prevNode;

        public Node(int num) {
            this.num = num;
        }
    }

}
