package binarytree;

public class SumOfNodesWithEvenValuedGrandParents {

    private TreeNodeContainer head;
    private TreeNodeContainer tail;
    private int collectedSum = 0;

    public static void main(String ar[]) {
        SumOfNodesWithEvenValuedGrandParents unit = new SumOfNodesWithEvenValuedGrandParents();
        TreeNode root = new TreeNode(6);
        TreeNode nodeOne = new TreeNode(5);
        TreeNode nodeTwo = new TreeNode(4);
        TreeNode nodeThree = new TreeNode(2);
        TreeNode nodeFour = new TreeNode(9);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeTwo.left = nodeFour;

        System.out.print("Sum of nodes whose grand parent is even numbered is : " + unit.sumEvenGrandparent(root));
    }

    public int sumEvenGrandparent(TreeNode root) {
        processToFindSumOfNodesWithEvenGrandParent(root);
        return collectedSum;
    }

    private void processToFindSumOfNodesWithEvenGrandParent(TreeNode node) {

        if (node != null) {
            TreeNode left = node.left;
            TreeNode right = node.right;

            if (head == null && tail == null) {
                head = new TreeNodeContainer(node);
                tail = head;
            } else {
                TreeNodeContainer treeNodeContainer = new TreeNodeContainer(node);
                tail.next = treeNodeContainer;
                treeNodeContainer.previous = tail;
                tail = treeNodeContainer;
            }

            if (left != null) {
                processToFindSumOfNodesWithEvenGrandParent(left);
            }

            if (right != null) {
                processToFindSumOfNodesWithEvenGrandParent(right);
            }

            if (tail != null && tail.previous != null && tail.previous.previous != null) {
                TreeNodeContainer treeNodeContainerGP =  tail.previous.previous;
                if (treeNodeContainerGP.node.val % 2 == 0) {
                    collectedSum+= tail.node.val;
                }
            }
            tail = tail.previous;
        }
    }

    static class TreeNodeContainer {
        private TreeNode node;
        private TreeNodeContainer next;
        private TreeNodeContainer previous;

        public TreeNodeContainer(TreeNode node) {
            this.node = node;
        }
    }
}
