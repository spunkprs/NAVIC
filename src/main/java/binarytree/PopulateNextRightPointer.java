package binarytree;

public class PopulateNextRightPointer {

    public static class TreeLinkNode {
        private int val;
        private TreeLinkNode left, right, next;
        public TreeLinkNode(int x) { val = x; }

        public void setLeft(TreeLinkNode left) {
            this.left = left;
        }

        public void setRight(TreeLinkNode right) {
            this.right = right;
        }
    }

    public void connect(TreeLinkNode root) {
        processToConnectNextRightPointer(root);
    }

    private void processToConnectNextRightPointer(TreeLinkNode node) {
        TreeLinkNode nodeOne = node;

        while (nodeOne != null) {
            TreeLinkNode nodeTwo = nodeOne;
            while (nodeTwo != null) {
                if (nodeTwo.left != null) {
                    if (nodeTwo.right != null) {
                        nodeTwo.left.next = nodeTwo.right;
                    } else {
                        nodeTwo.left.next = getNextRight(nodeTwo);
                    }
                }
                if (nodeTwo.right != null) {
                    nodeTwo.right.next = getNextRight(nodeTwo);
                }
                nodeTwo = nodeTwo.next;
            }
            if (nodeOne.left != null) {
                nodeOne = nodeOne.left;
            } else if (nodeOne.right != null) {
                nodeOne = nodeOne.right;
            } else {
                nodeOne = getNextRight(nodeOne);
            }
        }
    }

    private TreeLinkNode getNextRight(TreeLinkNode node) {
        TreeLinkNode n = node.next;
        while (n != null) {
            if (n.left != null) {
                return n.left;
            } else if (n.right != null) {
                return n.right;
            }
            n = n.next;
        }
        return null;
    }
}
