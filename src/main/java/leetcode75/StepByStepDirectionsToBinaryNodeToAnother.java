package leetcode75;

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
